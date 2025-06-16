package org.Model.Plan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PlanPremiumBaseTest {

    @Test
    void testDefaultConstructor() {
        PlanPremiumBase plan = new PlanPremiumBase();
        assertEquals(0, plan.getPoints(), "Default points should be 0");
    }

    @Test
    void testCopyConstructor() {
        Plan basePlan = new PlanPremiumBase();
        basePlan.setPoints(50);
        PlanPremiumBase copiedPlan = new PlanPremiumBase(basePlan);
        assertEquals(50, copiedPlan.getPoints(), "Copied plan should have the same points as the original");
    }

    @Test
    void testAddPoints() {
        PlanPremiumBase plan = new PlanPremiumBase();
        plan.addPoints();
        assertEquals(10, plan.getPoints(), "Points should increase by 10 after calling addPoints");
    }

    @Test
    void testCanAccessLibrary() {
        PlanPremiumBase plan = new PlanPremiumBase();
        assertTrue(plan.canAccessLibrary(), "PremiumBase users should have access to the library");
    }

    @Test
    void testCanSkip() {
        PlanPremiumBase plan = new PlanPremiumBase();
        assertTrue(plan.canSkip(), "PremiumBase users should be able to skip tracks");
    }

    @Test
    void testCanChooseWhatToPlay() {
        PlanPremiumBase plan = new PlanPremiumBase();
        assertTrue(plan.canChooseWhatToPlay(), "PremiumBase users should be able to choose what to play");
    }

    @Test
    void testHasAccessToFavorites() {
        PlanPremiumBase plan = new PlanPremiumBase();
        assertFalse(plan.hasAccessToFavorites(), "PremiumBase users should not have access to favorites");
    }

    @Test
    void testToString() {
        PlanPremiumBase plan = new PlanPremiumBase();
        plan.setPoints(20);
        String expected = "Plano: PremiumBase\n    Pontos: 20";
        assertEquals(expected, plan.toString(), "toString should return the correct representation of the plan");
    }

    @Test
    void testGetPlanName() {
        PlanPremiumBase plan = new PlanPremiumBase();
        assertEquals("PremiumBase", plan.getPlanName(), "getPlanName should return 'PremiumBase'");
    }
}