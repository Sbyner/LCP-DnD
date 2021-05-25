package mechanics.telegram;

import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import creatures.Creature;
import mechanics.Action;
import mechanics.Action.Type;
import mechanics.ActionProcessor;
import mechanics.narrators.Narrator;
import runtime.Fight;

public class Bot {

	TelegramBot bot;
	Fight fight;
	Narrator narrator;
	Creature current;

	public Bot(TelegramBot bot, Fight fight, Narrator narrator) {
		this.bot = bot;
		this.fight = fight;
		this.narrator = narrator;
		this.current = fight.next();
	}

	public Consumer<Update> getUpdateHandler() {
		return (update) -> {
			long chatId = update.message().chat().id();
			if(!fight.isOver()) {
			if (update.message().text().equalsIgnoreCase("/status")) {
				var status = narrator.getCharacterStatus(fight.getCreatures());
				SendResponse response = bot.execute(new SendMessage(chatId, current.getName() + "'s turn.\n"+status));
				return;
			}
			Action act = generateAction(update);
			if (validateAction(act)) {
				var narration = narrator.narrate(fight.step(current, act));
				current = fight.next();
				fight.turnLog.add(narration);
				SendResponse response = bot.execute(new SendMessage(chatId, narration));
				if(fight.isOver()) {
					var winnersCount = fight.getAliveCreatures().count();
					var winMessage = " won!";
					if(winnersCount>0) winMessage=fight.getAliveCreatures().map(Creature::getName).collect(Collectors.joining(", "))+winMessage;
					else winMessage = "Nobody" + winMessage + " You lose! Good day sir!"; 
					bot.execute(new SendMessage(chatId, winMessage));
				}
				
			} else {
				SendResponse response = bot.execute(new SendMessage(chatId, "Invalid input"));
			}
		} else {
			if (update.message().text().equalsIgnoreCase("/log")) {
				SendResponse response = bot.execute(new SendMessage(chatId, "Winners: "+fight.getAliveCreatures().map(Creature::getName).collect(Collectors.joining(", "))+"\n"+fight.turnLog.stream().collect(Collectors.joining("\n"))));
				return;
			}
			else {
				bot.execute(new SendMessage(chatId, "The fight is over, type /log to see an overview"));
			}
		}
		};

	}

	private String[] parseCommand(Message message) {
		var pattern = Pattern.compile("^/(\\S+)\\s(.*)$");
		var matcher = pattern.matcher(message.text());

		if (matcher.lookingAt()) {
			return new String[] { matcher.group(1), matcher.group(2) };
		} else {
			var secPatt = Pattern.compile("^/(\\S+)$");
			var secMtc = secPatt.matcher(message.text());
			if (secMtc.lookingAt()) {
				return new String[] { secMtc.group(1) };
			}
		}
		return new String[] { "", "" };
	}

	private Action generateAction(Update update) {
		var fields = parseCommand(update.message());
		switch (fields[0]) {
		case "cast": {
			var act = new Action();
			act.setType(Type.CAST);
			act.setOriginCreature(current);
			var fieldArr = fields[1].split("\\son\\s");
			if (fieldArr.length == 1) {
				act.setTargetCreature(current);
				act.setArgs(fieldArr[0].toLowerCase().replace(' ', '_'));
			} else if (fieldArr.length == 2) {
				act.setTargetCreature(fight.getCreatures().stream()
						.filter((x) -> x.getName().equalsIgnoreCase(fieldArr[1])).findAny().get());
				act.setArgs(fieldArr[0].toLowerCase().replace(' ', '_'));
			}
			return act;

//			
		}
		case "attack": {
			var act = new Action();
			act.setOriginCreature(current);
			act.setType(Type.ATTACK);
			act.setTargetCreature(fight.getCreatures().stream().filter((x) -> x.getName().equalsIgnoreCase(fields[1]))
					.findAny().get());
			return act;
		}
		case "help": {
			var act = new Action();
			act.setType(Type.HELP);
			act.setOriginCreature(current);
			act.setTargetCreature(fight.getCreatures().stream().filter((x) -> x.getName().equalsIgnoreCase(fields[1]))
					.findAny().get());
			return act;
		}
		case "dodge": {
			var act = new Action();
			act.setType(Type.DODGE);
			act.setOriginCreature(current);
			return act;
		}
		}
		return null;

	}

	private boolean validateAction(Action act) {
		if (act == null)
			return false;
		if (act.getOriginCreature() == null)
			return false;
		if (act.getOriginCreature().equals(act.getTargetCreature()))
			return false;
		if (act.getType() == null)
			return false;
		return true;
	}
}
