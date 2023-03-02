/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:19 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */
package edu.neumont.csc150.models.npc.secretbosses;

import edu.neumont.csc150.models.npc.bosses.Boss;

public interface SecretBoss extends Boss {
        int dropRareItem();
        int dropHealthUpgrade();
        int dropMpUpgrade();
}
