package utils;

import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;

/**
 *
 * @author mrkaczor
 */
public class CommonValidator {
    
    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private static final int TIN_LENGTH = 10;
    private static final int VIN_LENGTH = 17;
    // </editor-fold>
    
    public static void validateTIN(String sTIN) throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (sTIN.length() != TIN_LENGTH) {
            ed.addMessage("Numer NIP ma nieprawidłową długość (wymagane " + TIN_LENGTH + " znaków)");
        }
        for (int i = 0; i < sTIN.length(); i++) {
            if (sTIN.charAt(i) < 48 || sTIN.charAt(i) > 57) {
                ed.addMessage("Numer NIP jest niepoprawny (musi się składać wyłącznie z cyfr)");
                break;
            }
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("TIN validation failed", ed);
        }
    }
    
    public static void validateVIN(String sVIN) throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (sVIN.length() != VIN_LENGTH) {
            ed.addMessage("Numer VIN ma nieprawidłową długość (wymagane " + VIN_LENGTH + " znaków)");
        }
        for (int i = 0; i < sVIN.length(); i++) {
            char symbol = sVIN.charAt(i);
            //VIN should contain only digits [0-9] and letters [A-Z] without {I, O, Q}
            if (symbol<48 || (symbol>57 && symbol<65) || symbol>90 || symbol==73 || symbol==79 || symbol==81) {
                ed.addMessage("Numer VIN jest niepoprawny (musi się składać wyłącznie z cyfr oraz wielkich liter (z wyłączeniem I, O oraz Q))");
                break;
            }
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("VIN validation failed", ed);
        }
    }

}
