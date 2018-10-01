package org.gcalc;

public interface EquationEditorListener {
    /**
     * Signals to a listener that an EquationEditor's equation field has been
     * modified.
     *
     * @param id The id of the EquationEditor
     * @param equation The string contents of the EquationEditor's JTextField
     */
    void equationEdited(int id, Equation equation);
}
