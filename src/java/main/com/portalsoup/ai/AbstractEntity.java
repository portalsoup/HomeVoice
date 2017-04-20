package com.portalsoup.ai;

import java.util.*;

/**
 * Base class for entities, or AIs or whatever.
 */
public abstract class AbstractEntity {

    /**
     * List of behaviors that this entity performs.  Behaviors are triggered by certain String stimuli.
     */
    private List<AbstractComponent> behaviors;

    /**
     * This entity will only behaviorally react when awake.
     */
    private boolean awake;

    /**
     * The command used to wake this entity.
     */
    private String name;

    /**
     * For scheduling tasks to put the entity to sleep after inactivity.
     */
    private Timer wakeTimer;

    /**
     * How long should this entity be awake during inactivity?
     */
    private long wakeLength;
    /**
     * Use to track the latest timer task.  If a new task is scheduled before a previous
     * one finishes, then only the one which knows the matching taskId may execute.  Every
     * new task that is scheduled should update this field with a new value, and exclusively old it.
     */
    private UUID taskId;

    public AbstractEntity(String name) {
        this.behaviors = new ArrayList<>();
        this.name = name;
        this.awake = false;
        this.wakeTimer = new Timer();
    }

    /**
     * Ask this entity to react to a String stimuli if any behaviors are satisfied.  If multiple behaviors
     * are satisfied, then only the first will be used.  All queries will be ignored while this entity is asleep.
     * To wake this entity, pass in this entity's name.
     *
     * @param query
     * @return
     */
    public String ask(String query) {
        System.out.println("original query: " + query);
        boolean originalWakeStatus = isAwake();
        // If my name is called, wake me
        if (query.equals(name)) {
            wake();
            return "";
            // If I'm awake already and you're still talking to me, keep me awake
        } else if (originalWakeStatus) {
            wake();
        }

        if (isAwake()) {
            String response = process(query.trim().toLowerCase());
            if (!response.isEmpty()) {
                sleep();
                return response;
            }
        }
        return "";
    }

    /**
     * The entity is awake, so process the query.
     *
     * @param query
     * @return
     */
    protected abstract String process(String query);

    /**
     * Add a new behavior to this entity.
     *
     * @param component
     */
    public void addBehavior(AbstractComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("Can't add a null behavior.");
        }
        behaviors.add(component);
    }

    /**
     * Get a list of behaviors this entity has.
     *
     * @return
     */
    protected List<AbstractComponent> getBehaviors() {
        return new ArrayList<>(behaviors);
    }

    protected boolean isAwake() {
        return awake;
    }

    /**
     * Wakes this entity and schedules this entity to go back to sleep after {@link #wakeLength} milliseconds
     * of inactivity.
     */
    protected void wake() {
        System.out.println("Wake");
        awake = true;
        UUID uuid = UUID.randomUUID();
        this.taskId = uuid;
        wakeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!taskId.equals(uuid)) {
                    return;
                }
                awake = false;
                System.out.println("Sleep");
            }
        }, 5000);
    }

    /**
     * Put this entity to sleep immediately.  This method generates a new taskId rendering any currently
     * scheduled tasks obsolete.
     */
    protected void sleep() {
        taskId = UUID.randomUUID();
        awake = false;
        System.out.println("Sleep");
    }
}
