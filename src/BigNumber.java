import java.util.*;

/**
 * Author: Daniel Litvak
 * Date: 28.3.2018
 */
public class BigNumber
{
    private List digits;

    //TODO fill all doc and change the Linked list to what the course wants

    /**
     * Constructs a new BigNumber object. Initializes the big number with the value 0
     */
    public BigNumber()
    {
        digits = new LinkedList();
        ((LinkedList) digits).addFirst(0);
    }

    /**
     * Constructor that receives a number of the type long and converts it to a big number type
     *
     * @param number the number to convert
     */
    public BigNumber(long number)
    {
        digits = new LinkedList();

        do
        {
            // Add the last digit to the end
            ((LinkedList) digits).addLast((int) number % 10);
            // Cut one digit from the number
            number /= 10;
        } while (number > 0);
    }

    /**
     * A copy constructor for BigNumber
     *
     * @param number the BigNumber to copy
     */
    public BigNumber(BigNumber number)
    {
        // Check if the object exists
        if (number != null)
        {
            ListIterator iterator = number.digits.listIterator();
            do
            {
                // Take the first digit and put it to the end
                ((LinkedList) digits).addLast(iterator.next());
            } while (iterator.hasNext());
        }
    }

    /**
     * Returns a string representation of the BigNumber object
     *
     * @return a string representation of the BigNumber object
     */
    public String toString()
    {
        // Call for the recursive function
        return toString(digits.listIterator());
    }

    // The recursive function that prints the number the way regular number are printed from left to right
    private String toString(ListIterator iterator)
    {
        // If the node has a value
        if (iterator.hasNext())
        {
            int value = (int) iterator.next();
            // call the recursive function with the current result at the end of the string
            return toString(iterator) + value;
        }
        // if no value than return an empty string
        else
            return "";
    }

    /**
     * Gets a BigNumber and returns a BigNumber that is the sum of this BigNumber and the other
     *
     * @param other the number to add to this number
     * @return the sum of this and the other number
     */
    public BigNumber addBigNumber(BigNumber other)
    {
        BigNumber result = new BigNumber();
        // Remove the initial zero node
        result.digits.clear();

        ListIterator thisIterator = this.digits.listIterator();
        ListIterator otherIterator = other.digits.listIterator();

        int value;
        int carry = 0;
        int digit;
        // Keep adding as long both numbers got their digits
        while (thisIterator.hasNext() && otherIterator.hasNext())
        {
            // Add both last digits
            value = ((int) thisIterator.next() + (int) otherIterator.next());
            // The number to add is the modulus 10 of the value
            digit = value % 10;
            // Add the digit and Add the carry if it exists
            ((LinkedList) result.digits).addLast(digit + carry);
            // Set the carry if there should be a carry
            carry = value / 10;
        }
        // In case this number was not fully added continue
        while (thisIterator.hasNext())
        {
            value = (int) thisIterator.next();
            // Add the value with the carry from before
            ((LinkedList) result.digits).addLast(value + carry);
            // Reset the carry to 0
            carry = 0;
        }
        // In case other number was not fully added continue
        while (otherIterator.hasNext())
        {
            value = (int) otherIterator.next();
            // Add the value with the carry from before
            ((LinkedList) result.digits).addLast(value + carry);
            // Reset the carry to 0
            carry = 0;
        }
        // In case both numbers were at the same length and there is a carry left, add it to the new number
        if (carry > 0)
        {
            ((LinkedList) result.digits).addLast(carry);
        }
        return result;
    }

    /**
     * Gets a number of type long and return a BigNumber that is the sum of this and the other number
     *
     * @param num the number to add to this number
     * @return A BigNumber that is the sum of this and the numbers
     */
    public BigNumber addLong(long num)
    {
        BigNumber other = new BigNumber(num);//TODO change to one pass
        return addBigNumber(other);
    }

    /**
     * Gets another BigNumber and returns a comparison result
     *
     * @param other the number to compare to
     * @return 1 if this is bigger; -1 if other is bigger; 0 if they are equal
     */
    public int compareTo(BigNumber other)
    {
        int result = 0;
        ListIterator thisIterator = this.digits.listIterator();
        ListIterator otherIterator = other.digits.listIterator();
        int check;
        while (thisIterator.hasNext() && otherIterator.hasNext())
        {
            // Positive means this is bigger, negative means other is bigger, zero means no change
            check = (int) thisIterator.next() - (int) otherIterator.next();
            if (check > 0)
                result = 1;
            else if (check < 0)
                result = -1;
        }
        if (thisIterator.hasNext())
            result = 1;
        else if (otherIterator.hasNext())
            result = -1;
        return result;
    }

    /**
     * Gets a BigNumber and returns a BigNumber that is the subtraction of the bigger by the smaller one
     *
     * @param other the number to subtract with
     * @return the subtraction of the bigger number by the smaller number
     */
    public BigNumber subtractBigNumber(BigNumber other)
    {
        BigNumber big;
        BigNumber small;
        int compareResult = compareTo(other);
        // Check which number is bigger for future calculations
        if (compareResult > 0)
        {
            big = this;
            small = other;
        }
        else if (compareResult == 0)
        {
            return new BigNumber();
        }
        else
        {
            big = other;
            small = this;
        }
        BigNumber result = new BigNumber();
        // Remove the initial 0 node
        result.digits.clear();

        ListIterator bigIterator = big.digits.listIterator();
        ListIterator smallIterator = small.digits.listIterator();
        int borrow = 0;
        int value;
        while (smallIterator.hasNext() && bigIterator.hasNext())
        {
            // The digit to set is the subtract result minus the borrow
            value = (int) bigIterator.next() - borrow - (int) smallIterator.next();
            // If the value is negative borrow from the next digit of the big number
            if (value < 0)
            {
                value = 10 + value;
                borrow = 1;
            }
            ((LinkedList) result.digits).addLast(value);
        }
        // Continue adding the digits of the bigger number if left
        while (bigIterator.hasNext())
        {
            value = (int) bigIterator.next() - borrow;
            // If the last digit is zero, do not add it, just return the result
            if (value == 0 && !bigIterator.hasNext())
            {
                return result;
            }
            ((LinkedList) result.digits).addLast(value);
            borrow = 0;
        }
        return result;
    }

    /**
     * Gets a BigNumber and returns a BigNumber that is the multiplication of this BigNumber and the other
     *
     * @param other the number to multiply by
     * @return the result of the multiplication of this by the other
     */
    public BigNumber multiBigNumber(BigNumber other)
    {
        int numberOfZeroes;
        BigNumber result = new BigNumber();
        BigNumber tempBigNumber = new BigNumber();
        ListIterator otherIterator = other.digits.listIterator();
        ListIterator thisIterator = this.digits.listIterator();
        int value;
        int thisDigitCounter = 0;
        int otherDigitCounter = 0;
        // Pass over the digits of the this number
        while (thisIterator.hasNext())
        {
            int thisDigit = (int) thisIterator.next();
            // Pass over the digits of the other number
            while (otherIterator.hasNext())
            {
                // Calculate how many zeroes should be added
                numberOfZeroes = thisDigitCounter + otherDigitCounter;
                // Increase the number of digits passed of the other number
                otherDigitCounter++;
                int otherDigit = (int) otherIterator.next();

                value = thisDigit * otherDigit;
                tempBigNumber = tempBigNumber.addLong(value);
                // Add the missing zeroes
                for (int i = 0; i < numberOfZeroes; i++)
                {
                    ((LinkedList) tempBigNumber.digits).addFirst(0);
                }
                // Add the temporary to the final result
                result = result.addBigNumber(tempBigNumber);
                // Reset the temporary
                tempBigNumber = new BigNumber();
            }
            // Reset the other number of digits counter
            otherDigitCounter = 0;
            // Reset the iterator indicator
            otherIterator = other.digits.listIterator();
            thisDigitCounter++;

        }
        return result;
    }
}

