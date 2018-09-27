package org.gcalc;

public interface EquationListener {
    /**
     * Signals that a new equation has been created. A reference to the new
     * equation is supplied.
     *
     * @param newEquation The newly added equation
     */
    void equationAdded(Equation newEquation);

    /**
     * Called when one or more equations have been removed. An array containing
     * all remaining equations is supplied.
     *
     * @param remaining The remaining equations after the delete operation
     */
    void equationRemoved(Equation[] remaining);
}
