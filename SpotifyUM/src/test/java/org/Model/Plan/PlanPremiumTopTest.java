package org.Model.Plan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PlanPremiumTopTest {

    @Test
    void testDefaultConstructor() {
        PlanPremiumTop plan = new PlanPremiumTop();
        assertEquals(0, plan.getPoints(), "Default constructor should initialize points to 0");
    }

    @Test
    void testCopyConstructor() {
        Plan basePlan = new Plan() {
            @Override
            public boolean canAccessLibrary() {
                return false;
            }

            public String toString() {
                return "Base Plan";
            }

            public void addPoints() {
                setPoints(getPoints() + 10);
            }

            public String getPlanName() {
                return "Base Plan";
            }

            public boolean canSkip() {
                return false;
            }

            public boolean canChooseWhatToPlay() {
                return false;
            }

            public boolean hasAccessToFavorites() {
                return false;
            }
        };
        basePlan.setPoints(200);
        PlanPremiumTop plan = new PlanPremiumTop(basePlan);
        assertEquals(300, plan.getPoints(), "Copy constructor should add 100 points to the base plan's points");
    }

    @Test
    void testAddPoints() {
        PlanPremiumTop plan = new PlanPremiumTop();
        plan.setPoints(1000);
        plan.addPoints();
        assertEquals(1025, plan.getPoints(), "addPoints should add 2.5% of current points");
    }

    @Test
    void testCanAccessLibrary() {
        PlanPremiumTop plan = new PlanPremiumTop();
        assertTrue(plan.canAccessLibrary(), "PremiumTop users should have access to the library");
    }

    @Test
    void testCanSkip() {
        PlanPremiumTop plan = new PlanPremiumTop();
        assertTrue(plan.canSkip(), "PremiumTop users should be able to skip tracks");
    }

    @Test
    void testCanChooseWhatToPlay() {
        PlanPremiumTop plan = new PlanPremiumTop();
        assertTrue(plan.canChooseWhatToPlay(), "PremiumTop users should be able to choose what to play");
    }

    @Test
    void testHasAccessToFavorites() {
        PlanPremiumTop plan = new PlanPremiumTop();
        assertTrue(plan.hasAccessToFavorites(), "PremiumTop users should have access to favorites");
    }

    @Test
    void testToString() {
        PlanPremiumTop plan = new PlanPremiumTop();
        plan.setPoints(500);
        String expected = "Plano: PremiumTop\n    Pontos: 500";
        assertEquals(expected, plan.toString(), "toString should return the correct string representation");
    }

    @Test
    void testGetPlanName() {
        PlanPremiumTop plan = new PlanPremiumTop();
        assertEquals("PremiumTop", plan.getPlanName(), "getPlanName should return 'PremiumTop'");
    }
}