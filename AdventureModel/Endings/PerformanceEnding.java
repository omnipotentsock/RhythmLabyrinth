package AdventureModel.Endings;

import AdventureModel.Interactions.Action;
import AdventureModel.Interactions.SingleDialogue;
import AdventureModel.Movement.ForcedQueue;
import views.AdventureGameView;

import java.io.File;

public class PerformanceEnding implements Ending{

    private ForcedQueue queue = new ForcedQueue();

    private final String message = "You have performed well beyond what many others could ever imagine to achieve. A legendary hero!";
    private final String picture = "PerformanceEnding.png";
    /**
     * In this ending, the player has done exceptionally well.
     *      - Both a MiniGame and a Puzzle are rewarded
     *      - Afterward, display a PerformanceEnding message
     *      - Display PerformanceEnding picture
     */

    public PerformanceEnding(){
        this.queue.enqueue(new SingleDialogue("\"You've proven your worth well enough for you to challenge the GODS! Now come forth, and taste the wine of DEFEAT!\"-false"));
        this.queue.enqueue(new SingleDialogue("The Beast leaps at you!-false"));
        this.queue.enqueue(new Action("&M021&You dodge his attacks and swiftly deal a flurry of blows to his chest, causing him to howl in frustration."));
        this.queue.enqueue(new SingleDialogue("The Beast tears out the altar, and hurls it at you!-false"));
        this.queue.enqueue(new Action("&M022&You successfully leap into the air and dodge. Now's your chance!!"));
        this.queue.enqueue(new Action("&M023&The Beast opens his mouth to spit one last retort at you, but he breathes his last before he manages to.-false"));
        this.queue.enqueue(new SingleDialogue(this.message + "-false"));
    }
    @Override
    public void executeEnding() {
        throw new UnsupportedOperationException();
    }
    @Override
    public String getID() {
        return "PERFORMANCE";
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public String getPicture() {
        return picture;
    }

    @Override
    public ForcedQueue getQueue() {
        return queue;
    }
}
