package AdventureModel.Endings;

import AdventureModel.Interactions.Action;
import AdventureModel.Interactions.Choice;
import AdventureModel.Interactions.SingleDialogue;
import AdventureModel.Movement.ForcedQueue;
import views.AdventureGameView;

import java.io.File;

public class SatisfactoryEnding implements Ending{

    private ForcedQueue queue = new ForcedQueue();

    private final String message = "You have saved the world, young one. Well done!";
    private final String picture = "SatisfactoryEnding";
    /**
     * In this ending, the player achieves at least the minimum requirements to win the game.
     *      - Play either a Puzzle or Battle
     *      - Afterward, display picture
     *      - Display message
     */

    public SatisfactoryEnding(){
        this.queue.enqueue(new SingleDialogue("\"Oh hoh ho, little rat. You dare challenge me!? Time to squash you.\"-false"));
        this.queue.enqueue(new SingleDialogue("The Beast stands still, awaiting your move.-false"));
        this.queue.enqueue(new Choice("Do you attack him head on, or try to find a weakness?-false-FIGHT>&M012&You take a beating but you land a punch to his abdomen./WEAKNESS>&M013&You dodge around and land a punch to his abdomen."));
        this.queue.enqueue(new SingleDialogue("The Beast gasps in surprise. He smirks and moves back.-false"));
        this.queue.enqueue(new SingleDialogue("\"You're quite strong. If only you'd done a better job on your way here, I might fight you at full force.\" Saying that, he retreats into the shadows, never to be seen again.-false"));
        this.queue.enqueue(new SingleDialogue(this.message + "-false"));
    }
    @Override
    public void executeEnding() {
        throw new UnsupportedOperationException();
    }
    @Override
    public String getID() {
        return "SATISFACTORY";
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
