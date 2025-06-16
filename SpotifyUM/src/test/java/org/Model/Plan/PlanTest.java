package org.Model.Plan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for the Plan class.
 * Since Plan is abstract, we will use a concrete subclass for testing purposes.
 */
class PlanTest {

    // A concrete subclass of Plan for testing purposes
    static class TestPlan extends Plan {
        @Override
        public boolean canAccessLibrary() {
            return true;
        }

        @Override
        public boolean canSkip() {
            return false;
        }

        @Override
        public boolean canChooseWhatToPlay() {
            return true;
        }

        @Override
        public boolean hasAccessToFavorites() {
            return false;
        }

        @Override
        public void addPoints() {
            setPoints(getPoints() + 10);
        }

        @Override
        public String toString() {
            return "TestPlan";
        }

        @Override
        public String getPlanName() {
            return "Test Plan";
        }
    }

    @Test
    void testGetAndSetPoints() {
        Plan plan = new TestPlan();
        plan.setPoints(50);
        assertEquals(50, plan.getPoints());
    }

    @Test
    void testAddPoints() {
        Plan plan = new TestPlan();
        plan.setPoints(20);
        plan.addPoints();
        assertEquals(30, plan.getPoints());
    }

    @Test
    void testCanAccessLibrary() {
        Plan plan = new TestPlan();
        assertTrue(plan.canAccessLibrary());
    }

    @Test
    void testCanSkip() {
        Plan plan = new TestPlan();
        assertFalse(plan.canSkip());
    }

    @Test
    void testCanChooseWhatToPlay() {
        Plan plan = new TestPlan();
        assertTrue(plan.canChooseWhatToPlay());
    }

    @Test
    void testHasAccessToFavorites() {
        Plan plan = new TestPlan();
        assertFalse(plan.hasAccessToFavorites());
    }

    @Test
    void testToString() {
        Plan plan = new TestPlan();
        assertEquals("TestPlan", plan.toString());
    }

    @Test
    void testGetPlanName() {
        Plan plan = new TestPlan();
        assertEquals("Test Plan", plan.getPlanName());
    }
}