package org.gcalc;

public interface EquationListener {
    /**
     * Signals that a new equation has been created. A reference to the new
     * equation is supplied.
     *
     * @param id The array index of the new equation
     * @param newEquation The newly added equation
     */
    void equationAdded(int id, Equation newEquation);

    /**
     * Called when one or more equations have been removed.
     *
     * @param id The array index of the removed equation
     */
    void equationRemoved(int id);

    /**
     * Called when a pre-existing equation has been modified. Note that the new
     * equation instance may be the same instance as the previous instance.
     *
     * @param id The array index of the modified equation
     * @param e The equation object to replace the old one with
     */
    void equationChanged(int id, Equation e);
}
