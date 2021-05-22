package mechanics.telegram;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import creatures.Creature;
import mechanics.Action;
import mechanics.ActionProcessor;

public class Bot implements ActionProcessor{

	TelegramBot bot;
	Future<Action> future;
	public Bot(TelegramBot bot) {
		this.bot = bot;
	}

	public Consumer<Update> getUpdateHandler(){
		return (update) -> {
			long chatId = update.message().chat().id();
			if(update.message().text().equals("cucco")) {
				bot.execute(new SendMessage(chatId, "Hello cucco!"));
			}
			else
			bot.execute(new SendMessage(chatId, "Hello!"));
		};
		
	}

	@Override
	public Action feed(Creature originCreature, List<Creature> creatures) {
	
		return null;
	}

	@Override
	public void processAction(Action action) {
		// TODO Auto-generated method stub
		
	}
}
