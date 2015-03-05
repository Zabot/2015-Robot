/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 */
public class UpdateableManager {
    private static final UpdateableManager INSTANCE = new UpdateableManager();
    public static UpdateableManager getInstancce() { return INSTANCE; }
    
    private final Queue<Updateable> queue = new LinkedList<>();
    private volatile boolean running;
    
    public void start() {
        System.out.println("Started");

        Thread t = new Thread(this::update);
        t.setName("Updateable");
        running = true;
        t.start();
    }
    
    private void update() {
        while(running) {
            if(!queue.isEmpty()) {
                Updateable u = queue.poll();
                u.update(Robot.time());
                queue.offer(u);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void register(Updateable r) {
        queue.offer(r);
    }
}
