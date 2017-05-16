package ifpb.pod.sender;

import ifpb.pod.sender.repositories.MessageRepository;
import ifpb.pod.sender.repositories.SendedMessageRepository;

import java.util.Timer;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class TaskManager {

    public static void runTask(MessageRepository repository, SendedMessageRepository sendedRepository) {
        Timer timer = new Timer();
        timer.schedule(new SendTask(repository, sendedRepository), 3000, 10000);
    }
}
