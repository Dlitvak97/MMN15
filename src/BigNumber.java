import java.util.*;

/**
 * Author: Daniel Litvak
 * Date: 28.3.2018
 */
public class BigNumber
{
    // The head of the list of all the digits of the number ordered from least s
    private IntNode _lastDigit;
    //TODO fill all doc and change the Linked list to what the course wants

    /**
     * Constructs a new BigNumber object. Initializes the big number with the value 0
     */
    public BigNumber()
    {
        // set the value of the digit to 0
        _lastDigit = new IntNode(0, null);
    }

    /**
     * Constructor that receives a number of the type long and converts it to a big number type
     *
     * @param number the number to convert
     */
    public BigNumber(long number)
    {
        _lastDigit = new IntNode((int) number % 10, null);
        // drop the last digit from the number after it was added to the list
        number /= 10;
        IntNode currentDigit = _lastDigit;
        while (number > 0)
        {
            // Add the latest digit after the current digit for the order of the list to be from units to tens to
            // hundreds and so on
            currentDigit.setNext(new IntNode((int) number % 10, null));
            // Change the current digit to add the next digit after the digit before that
            currentDigit = currentDigit.getNext();
            // Drop the last digit from the number after it was added to the list
            number /= 10;
        }
    }

    /**
     * A copy constructor for BigNumber
     *
     * @param other the BigNumber to copy
     */
    public BigNumber(BigNumber other)
    {
        // Check if the object exists
        if (other != null)
        {
            // Create a node to travel the other BigNumber
            IntNode otherCurrentDigit = other._lastDigit;

            // add the last digit to this BigNumber
            _lastDigit = new IntNode(otherCurrentDigit.getValue(), null);

            // Create a node to travel this BigNumber
            IntNode thisCurrentDigit = _lastDigit;

            // Advance the other digit to add the next digit to the new number
            otherCurrentDigit = otherCurrentDigit.getNext();

            while (otherCurrentDigit != null)
            {
                // set the next digit of this BigNumber to to be the next digit of the other BigNUmber
                thisCurrentDigit.setNext(new IntNode(otherCurrentDigit.getValue(), null));

                // Change the current digit of this BigNumber to add the next digit of the other BigNumber
                // after the digit before that
                thisCurrentDigit = thisCurrentDigit.getNext();

                // Advance the other digit to add the next digit to the new number
                otherCurrentDigit = otherCurrentDigit.getNext();
            }
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
        return toString(_lastDigit);
    }

    // The recursive function that prints the number the way regular number are printed from left to right
    private String toString(IntNode node)
    {
        // If the node has a value
        if (node != null)
        {
            // keep the nodes value
            int value = node.getValue();
            // call the recursive function with the current nodes value at the end of the string
            return toString(node.getNext()) + value;
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
        // The sum of the digits addition
        int sum;

        // The carry that may occur if the sum is over 9
        int carry;

        // The digit to put in the result BigNumber
        int digit;
        BigNumber result = new BigNumber();

        // The nodes to travel the digit lists
        IntNode thisCurrent = _lastDigit;
        IntNode otherCurrent = other._lastDigit;
        IntNode resultCurrent = result._lastDigit;

        // Add both digits
        sum = thisCurrent.getValue() + otherCurrent.getValue();

        // The digit to put is the modulus 10 of the sum
        digit = sum % 10;

        // Put the digit in the new BigNumber
        resultCurrent.setValue(digit);

        // Set the carry if there should be a carry
        carry = sum / 10;

        // Advance with the digits
        thisCurrent = thisCurrent.getNext();
        otherCurrent = otherCurrent.getNext();

        // Keep adding as long both numbers got their digits
        while (thisCurrent != null && otherCurrent != null)
        {
            // Add both digits
            sum = thisCurrent.getValue() + otherCurrent.getValue() + carry;

            // The digit to put is the modulus 10 of the sum
            digit = sum % 10;

            // Put the digit
            resultCurrent.setNext(new IntNode(digit,null));

            // Set the carry if there should be a carry
            carry = sum / 10;

            // Advance with the digits
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
            resultCurrent =  resultCurrent.getNext();
        }

        // In case this number was not fully added continue
        while (thisCurrent != null)
        {
            // Calculate the value, and the digit
            sum = thisCurrent.getValue() + carry;
            digit = sum % 10;

            // Put the digit in the result
            resultCurrent.setNext(new IntNode(digit,null));

            // Calculate the carry
            carry = sum / 10;

            // Advance with the digits
            thisCurrent = thisCurrent.getNext();
            resultCurrent = resultCurrent.getNext();
        }

        // In case other number was not fully added continue
        while (otherCurrent != null)
        {
            // Calculate the value, and the digit
            sum = otherCurrent.getValue() + carry;
            digit = sum % 10;

            // Put the digit in the result
            resultCurrent.setNext(new IntNode(digit,null));

            // Calculate the carry
            carry = sum / 10;

            // Advance with the digits
            otherCurrent = otherCurrent.getNext();
            resultCurrent = resultCurrent.getNext();
        }
        // In case there is a carry left, add it to the result
        if (carry > 0)
        {
            // Put the carry in the result
            resultCurrent.setNext(new IntNode(carry,null));
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
        // The sum of the digits addition
        int sum;

        // The carry that may occur if the sum is over 9
        int carry;

        // The digit to put in the result BigNumber
        int digit;
        BigNumber result = new BigNumber();

        // The nodes to travel the digit lists
        IntNode thisCurrent = _lastDigit;
        IntNode resultCurrent = result._lastDigit;

        // Add both digits
        sum = thisCurrent.getValue() + (int)(num % 10);

        // The digit to put is the modulus 10 of the sum
        digit = sum % 10;

        // Put the digit in the new BigNumber
        resultCurrent.setValue(digit);

        // Set the carry if there should be a carry
        carry = sum / 10;

        // Advance with the digits
        thisCurrent = thisCurrent.getNext();
        num /= 10;

        // Keep adding as long both numbers got their digits
        while (thisCurrent != null && num != 0)
        {
            // Add both digits
            sum = thisCurrent.getValue() + (int)(num % 10) + carry;

            // The digit to put is the modulus 10 of the sum
            digit = sum % 10;

            // Put the digit
            resultCurrent.setNext(new IntNode(digit,null));

            // Set the carry if there should be a carry
            carry = sum / 10;

            // Advance with the digits
            thisCurrent = thisCurrent.getNext();
            num /= 10;
            resultCurrent =  resultCurrent.getNext();
        }

        // In case this number was not fully added continue
        while (thisCurrent != null)
        {
            // Calculate the value, and the digit
            sum = thisCurrent.getValue() + carry;
            digit = sum % 10;

            // Put the digit in the result
            resultCurrent.setNext(new IntNode(digit,null));

            // Calculate the carry
            carry = sum / 10;

            // Advance with the digits
            thisCurrent = thisCurrent.getNext();
            resultCurrent = resultCurrent.getNext();
        }

        // In case other number was not fully added continue
        while (num != 0)
        {
            // Calculate the value, and the digit
            sum = (int)(num % 10) + carry;
            digit = sum % 10;

            // Put the digit in the result
            resultCurrent.setNext(new IntNode(digit,null));

            // Calculate the carry
            carry = sum / 10;

            // Advance with the digits
            num /= 10;
            resultCurrent = resultCurrent.getNext();
        }
        // In case there is a carry left, add it to the result
        if (carry > 0)
        {
            // Put the carry in the result
            resultCurrent.setNext(new IntNode(carry,null));
        }
        return result;
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
        IntNode thisCurrent = _lastDigit;
        IntNode otherCurrent = other._lastDigit;
        int check;
        while (thisCurrent != null && otherCurrent != null)
        {
            // Positive means this is bigger, negative means other is bigger, zero means no change
            check = thisCurrent.getValue() - otherCurrent.getValue();
            // Change the check appropriately if the numbers are different
            if (check > 0)
                result = 1;
            else if (check < 0)
                result = -1;

            // Advance the traveling nodes
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }
        // If one of the numbers is longer it means it is bigger
        if (thisCurrent != null)
            result = 1;
        else if (otherCurrent != null)
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
            // Other is smaller than this
            big = this;
            small = other;
        }
        else if (compareResult == 0)
        {
            return new BigNumber();
        }
        else
        {
            // Other is bigger than this
            big = other;
            small = this;
        }
        BigNumber result = new BigNumber();

        // The nodes to travel the digit lists
        IntNode bigCurrent = big._lastDigit;
        IntNode smallCurrent = small._lastDigit;
        IntNode resultCurrent = result._lastDigit;

        int borrow = 0;
        int difference;
        int digit;
        int zeroCounter = 0;

        // Calculate the difference between the big number and the small number
        difference = bigCurrent.getValue() - smallCurrent.getValue();

        // if the difference is negative
        if (difference < 0)
        {
            digit = 10 + difference;
            borrow = 1;
        }
        else
        {
            digit = difference;
        }

        // Put the digit in the new BigNumber
        resultCurrent.setValue(digit);

        // Advance the traveling digit nodes
        bigCurrent = bigCurrent.getNext();
        smallCurrent = smallCurrent.getNext();

        while (smallCurrent != null && bigCurrent != null)
        {
            // The digit to set is the subtract result minus the borrow
            difference = bigCurrent.getValue() - borrow - smallCurrent.getValue();

            // If the value is negative borrow from the next digit of the big number
            if (difference < 0)
            {
                digit = 10 + difference;
                borrow = 1;
            }
            else
            {
                digit = difference;
                borrow = 0;
            }
            // Check if the digit is zero
            if (digit == 0)
            {
                // If the digit is 0 count it instead of putting it in the BigNumber
                zeroCounter++;
            }
            else
            {
                // Check if there are zeros to put before the digit
                if (zeroCounter > 0)
                {
                    // put the required amount of zeroes before the digit
                    while (zeroCounter > 0)
                    {
                        resultCurrent.setNext(new IntNode(0, null));
                        resultCurrent = resultCurrent.getNext();
                        zeroCounter--;
                    }
                }
                resultCurrent.setNext(new IntNode(digit, null));
                resultCurrent = resultCurrent.getNext();
            }


            // Advance the traveling digit nodes
            bigCurrent = bigCurrent.getNext();
            smallCurrent = smallCurrent.getNext();
        }
        // At this state the method must have finished processing the smaller number

        // Continue processing the digits of the bigger number if left
        while (bigCurrent != null)
        {
            difference = bigCurrent.getValue() - borrow;

            // If the value is negative borrow from the next digit of the big number
            if (difference < 0)
            {
                digit = 10 + difference;
                borrow = 1;
            }
            else
            {
                digit = difference;
                borrow = 0;
            }
            // Check if the digit is zero
            if (digit == 0)
            {
                // If the digit is 0 count it instead of putting it in the BigNumber
                zeroCounter++;
            }
            else
            {
                // Check if there are zeros to put before the digit
                if (zeroCounter > 0)
                {
                    // put the required amount of zeroes before the digit
                    while (zeroCounter > 0)
                    {
                        resultCurrent.setNext(new IntNode(0, null));
                        resultCurrent = resultCurrent.getNext();
                        zeroCounter--;
                    }
                }
                resultCurrent.setNext(new IntNode(digit, null));
                resultCurrent = resultCurrent.getNext();
            }

            // Advance with the digit node
            bigCurrent = bigCurrent.getNext();
        }
        return result;
    }

    /**
     * Gets a BigNumber and returns a BigNumber that is the multiplication of this BigNumber and the other
     *
     * @param other the number to multiply by
     * @return the result of the multiplication of this by the other
     */
    public BigNumber multBigNumber(BigNumber other)
    {
        int numberOfZeroes;
        BigNumber result = new BigNumber();
        BigNumber tempBigNumber = new BigNumber();

        // Traveling digit nodes
        IntNode thisCurrent = _lastDigit;
        IntNode otherCurrent = other._lastDigit;
        IntNode resultCurrent = result._lastDigit;
        IntNode tempCurrent;

        int product;
        int thisDigitCounter = 0;
        int otherDigitCounter = 0;
        // Pass over the digits of the this number
        while (thisCurrent != null)
        {
            int thisDigit = thisCurrent.getValue();
            // Pass over the digits of the other number
            while (otherCurrent != null)
            {
                // Calculate how many zeroes should be added
                numberOfZeroes = thisDigitCounter + otherDigitCounter;

                // Increase the number of digits passed of the other number
                otherDigitCounter++;
                int otherDigit = otherCurrent.getValue();

                // Calculate the product of the two digits
                product = thisDigit * otherDigit;

                // Add the product to temp
                tempBigNumber = tempBigNumber.addLong(product);

                // If zeroes need to be added to temp
                if (numberOfZeroes > 0 && product != 0)
                {
                    tempCurrent = tempBigNumber._lastDigit;

                    // Add the missing zeroes in the beginning of the list
                    for (int i = 0; i < numberOfZeroes; i++)
                    {
                        // Create a node with zero and connect it to the head of the list to add a zero
                        // in the beginning of the digit list
                        tempCurrent = new IntNode(0, tempCurrent);
                    }

                    // Change the digit list to start from the last zero added
                    tempBigNumber._lastDigit = tempCurrent;
                }

                // Add temp to the final result
                result = result.addBigNumber(tempBigNumber);

                // Reset the temporary
                tempBigNumber = new BigNumber();

                // Advance the digit node of other BigNumber
                otherCurrent = otherCurrent.getNext();
            }
            // Reset the other number of digits counter
            otherDigitCounter = 0;
            // Reset the digit node of other BigNumber
            otherCurrent = other._lastDigit;
            thisDigitCounter++;

            // advance the digit node of this BigNumber
            thisCurrent = thisCurrent.getNext();
        }
        return result;
    }
}

