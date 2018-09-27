package org.gcalc;

import java.security.InvalidParameterException;

public class Equation {
    public Equation(String rawEquation) throws InvalidParameterException {
        rawEquation = rawEquation.replaceAll(" ", "");
        String[] equationParts = rawEquation.split("=");

        if (equationParts.length != 2)
            throw new InvalidParameterException(
                    "Equation must not contain multiple equalities");

        equationParts[1] = rearrange(equationParts[0], equationParts[1]);
        equationParts[0] = "x";
    }

    /**
     * Rearranges an equation to be expressed in terms of y.
     *
     * @param lhs Expression on left side of equals sign
     * @param rhs Expression on right side of equals sign
     * @return The expression on the right side of the rearranged equation's
     *         equals sign (left side is implied to be `y=`)
     */
    private String rearrange(String lhs, String rhs) {
        return null;
    }
}
