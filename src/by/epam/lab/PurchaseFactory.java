package by.epam.lab;

import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.beans.Purchase;
import by.epam.lab.exceptions.CsvLineException;
import by.epam.lab.exceptions.NonPositiveArgumentException;
import by.epam.lab.exceptions.WrongArgumentTypeException;

import static by.epam.lab.Constants.*;

public class PurchaseFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            protected Purchase getPurchase(String[] elements) throws CsvLineException {
                return new Purchase(elements);
            }
        },
        PRICE_DISCOUNT {
            protected Purchase getPurchase(String[] elements) throws CsvLineException {
                return new PriceDiscountPurchase(elements);
            }
        };

        abstract protected Purchase getPurchase(String[] elements) throws CsvLineException;
    }

    private static PurchaseKind getPurchaseKind (int elementsLength) {
        PurchaseKind purchaseKind = PurchaseKind.GENERAL_PURCHASE;
        if (elementsLength == MAX_PURCHASE_LENGTH){
            purchaseKind = PurchaseKind.PRICE_DISCOUNT;
        }
        return purchaseKind;
    }

    public static Purchase getPurchaseFromFactory(String csvLine) throws CsvLineException {
        String[] elements = csvLine.split(DELIMITER);
        try {
            return getPurchaseKind(elements.length).getPurchase(elements);
        } catch (IndexOutOfBoundsException e) {
            throw new CsvLineException(csvLine, WRONG_ARGUMENT_MESSAGE);
        } catch (NumberFormatException e) {
            throw new CsvLineException(csvLine, WRONG_ARGUMENT_PRICE_AND_UNITS);
        } catch (CsvLineException e) {
            throw new CsvLineException(csvLine, EMPTY_ELEMENT, NAME);
        } catch (NonPositiveArgumentException e) {
            throw new CsvLineException(csvLine, e.getWrongField());
        } catch (WrongArgumentTypeException e) {
            throw new CsvLineException(csvLine, e.getWrongField());
        }
    }
}
