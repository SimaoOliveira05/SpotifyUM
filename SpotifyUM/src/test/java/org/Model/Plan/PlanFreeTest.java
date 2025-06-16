package org.Model.Plan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for PlanFree.
 */
public class PlanFreeTest {

    @Test
    public void testDefaultConstructor() {
        PlanFree plan = new PlanFree();
        assertEquals(0, plan.getPoints(), "Default points should be 0");
    }

    @Test
    public void testCopyConstructor() {
        PlanFree originalPlan = new PlanFree();
        originalPlan.addPoints();
        PlanFree copiedPlan = new PlanFree(originalPlan);
        assertEquals(originalPlan.getPoints(), copiedPlan.getPoints(), "Copied plan should have the same points as the original");
    }

    @Test
    public void testAddPoints() {
        PlanFree plan = new PlanFree();
        plan.addPoints();
        assertEquals(5, plan.getPoints(), "Points should increase by 5 after calling addPoints");
    }

    @Test
    public void testCanAccessLibrary() {
        PlanFree plan = new PlanFree();
        assertFalse(plan.canAccessLibrary(), "Free plan users should not have access to the library");
    }

    @Test
    public void testCanSkip() {
        PlanFree plan = new PlanFree();
        assertFalse(plan.canSkip(), "Free plan users should not be able to skip tracks");
    }

    @Test
    public void testCanChooseWhatToPlay() {
        PlanFree plan = new PlanFree();
        assertFalse(plan.canChooseWhatToPlay(), "Free plan users should not be able to choose what to play");
    }

    @Test
    public void testHasAccessToFavorites() {
        PlanFree plan = new PlanFree();
        assertFalse(plan.hasAccessToFavorites(), "Free plan users should not have access to favorites");
    }

    @Test
    public void testToString() {
        PlanFree plan = new PlanFree();
        String expected = "Plano: Free\n    Pontos: 0";
        assertEquals(expected, plan.toString(), "toString should return the correct representation of the plan");
    }

    @Test
    public void testGetPlanName() {
        PlanFree plan = new PlanFree();
        assertEquals("Free", plan.getPlanName(), "getPlanName should return 'Free'");
    }
}