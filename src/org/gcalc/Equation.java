package org.gcalc;

import java.security.InvalidParameterException;

public class Equation {
    public Equation(String rawEquation) throws InvalidParameterException {
        rawEquation = rawEquation.replaceAll(" ", "");
        String[] equationParts = rawEquation.split("=");

        if (equationParts.length != 2)
            throw new InvalidParameterException(
                    "Equation must not contain multiple equalities");

        equationParts = rearrange(equationParts[0], equationParts[1]);
    }

    private String[] rearrange(String lhs, String rhs) {
        return null;
    }
}
