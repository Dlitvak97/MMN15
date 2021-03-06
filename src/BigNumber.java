/**
 * This class represents a large number object.
 *
 * Author: Daniel Litvak
 * Date: 10.6.2018
 */

public class BigNumber
{
    // The head of the digit list. Starts from the units and continues to tens, hundreds and so on.
    private IntNode _lastDigit;

    /**
     * Constructs a new BigNumber object. Initializes the big number with the value 0.
     *
     * time complexity: O(1)
     * space complexity: O(1)
     */
    public BigNumber()
    {
        // set the value of the digit to 0
        _lastDigit = new IntNode(0, null);
    }

    /**
     * Constructor that receives a number of the type long and converts it to a BigNumber type.
     *
     * time complexity: O(n)
     * space complexity: O(n)
     *
     * @param number the number to convert
     */
    public BigNumber(long number)
    {
        // Put the unit in the digit list
        _lastDigit = new IntNode((int) (number % 10), null);

        // Drop the last digit from the number after it was added to the list
        number /= 10;

        IntNode currentDigit = _lastDigit;
        while (number > 0)
        {
            // Add the latest digit after the current digit so the order of the list will be from units to tens to
            // hundreds and so on
            currentDigit.setNext(new IntNode((int) (number % 10), null));

            // Advance with the digit node
            currentDigit = currentDigit.getNext();
            // Drop the last digit from the number after it was added to the list
            number /= 10;
        }
    }

    /**
     * A copy constructor for BigNumber
     *
     * time complexity: O(n)
     * space complexity: O(n)
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

            // Advance the other digit
            otherCurrentDigit = otherCurrentDigit.getNext();

            while (otherCurrentDigit != null)
            {
                // set the next digit of this BigNumber to to be the next digit of the other BigNUmber
                thisCurrentDigit.setNext(new IntNode(otherCurrentDigit.getValue(), null));

                // Advance the current digit node
                thisCurrentDigit = thisCurrentDigit.getNext();

                // Advance the other digit to add the next digit to the new number
                otherCurrentDigit = otherCurrentDigit.getNext();
            }
        }
    }

    /**
     * Returns a string representation of the BigNumber object
     *
     * time complexity: O(n)
     * space complexity: O(n)
     *
     * @return a string representation of the BigNumber object
     */
    public String toString()
    {
        // Call for the recursive function with the digits list
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
     * time complexity: O(n)
     * space complexity: O(n)
     *
     * @param other the number to add to this number
     * @return a BigNumber that is the sum of this and the other number
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

        // Keep adding as long as both numbers have their digits
        while (thisCurrent != null && otherCurrent != null)
        {
            // Add both digits
            sum = thisCurrent.getValue() + otherCurrent.getValue() + carry;

            // The digit to put is the modulus 10 of the sum
            digit = sum % 10;

            // Put the digit
            resultCurrent.setNext(new IntNode(digit, null));

            // Set the carry if there should be a carry
            carry = sum / 10;

            // Advance with the digits
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
            resultCurrent = resultCurrent.getNext();
        }

        // In case this number was not fully added continue adding it to the result
        while (thisCurrent != null)
        {
            // Calculate the value, and the digit
            sum = thisCurrent.getValue() + carry;
            digit = sum % 10;

            // Put the digit in the result
            resultCurrent.setNext(new IntNode(digit, null));

            // Calculate the carry
            carry = sum / 10;

            // Advance with the digits
            thisCurrent = thisCurrent.getNext();
            resultCurrent = resultCurrent.getNext();
        }

        // In case other number was not fully added continue adding it to the result
        while (otherCurrent != null)
        {
            // Calculate the value, and the digit
            sum = otherCurrent.getValue() + carry;
            digit = sum % 10;

            // Put the digit in the result
            resultCurrent.setNext(new IntNode(digit, null));

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
            resultCurrent.setNext(new IntNode(carry, null));
        }
        return result;
    }

    /**
     * Gets a number of type long and return a BigNumber that is the sum of this and the other number
     *
     * time complexity: O(n)
     * space complexity: O(n)
     *
     * @param num the number to add to this number
     * @return A BigNumber that is the sum of this and the other number
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
        sum = thisCurrent.getValue() + (int) (num % 10);

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
            sum = thisCurrent.getValue() + (int) (num % 10) + carry;

            // The digit to put is the modulus 10 of the sum
            digit = sum % 10;

            // Put the digit
            resultCurrent.setNext(new IntNode(digit, null));

            // Set the carry if there should be a carry
            carry = sum / 10;

            // Advance with the digits
            thisCurrent = thisCurrent.getNext();
            num /= 10;
            resultCurrent = resultCurrent.getNext();
        }

        // In case this number was not fully added continue
        while (thisCurrent != null)
        {
            // Calculate the value, and the digit
            sum = thisCurrent.getValue() + carry;
            digit = sum % 10;

            // Put the digit in the result
            resultCurrent.setNext(new IntNode(digit, null));

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
            sum = (int) (num % 10) + carry;
            digit = sum % 10;

            // Put the digit in the result
            resultCurrent.setNext(new IntNode(digit, null));

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
            resultCurrent.setNext(new IntNode(carry, null));
        }
        return result;
    }

    /**
     * Gets another BigNumber and returns a comparison result
     *
     * time complexity: O(n)
     * space complexity: O(1)
     *
     * @param other the number to compare to
     * @return 1 if other is smaller; -1 if other is bigger; 0 if they are equal
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

            // Advance the traveling digit nodes
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
     * time complexity: O(n)
     * space complexity: O(n)
     *
     * @param other the number to subtract with
     * @return the subtraction of the bigger number by the smaller number
     */
    public BigNumber subtractBigNumber(BigNumber other)
    {
        BigNumber big;
        BigNumber small;
        int compareResult = compareTo(other);

        // Check which number is bigger in order to subtract the smaller from the bigger
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

        // If the difference is negative borrow from the next digit of the big number
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
            // The digit to set is the subtraction result minus the borrow from the subtraction before
            difference = bigCurrent.getValue() - borrow - smallCurrent.getValue();

            // If the difference is negative borrow from the next digit of the big number
            if (difference < 0)
            {
                digit = 10 + difference;
                borrow = 1;
            }
            else
            {
                digit = difference;

                // If not borrowed from the next digit, reset the borrow variable
                borrow = 0;
            }

            // Check if the digit is zero to avoid removing zeroes at the end in case there is no more digits
            if (digit == 0)
            {
                // If the digit is 0 count it instead of putting it in the BigNumber
                zeroCounter++;
            }
            else
            {
                // Check if there are zeros to put before the digit, if the zero counter is larger than 0 it means
                // some zeros were counted and not added to the BigNumber so they need to be added before the new digit
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

                // Advance the result digit node
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

                // If not borrowed from the next digit, reset the borrow variable
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

                // Advance the result digit node
                resultCurrent = resultCurrent.getNext();
            }

            // Advance with the digit node
            bigCurrent = bigCurrent.getNext();
        }
        return result;
    }

    /**
     * Gets a BigNumber and returns a BigNumber that is the product of the multiplication of this BigNumber and
     * other BigNumber
     *
     * time complexity:O(n^2)
     * space complexity: O(n)
     *
     * @param other the number to multiply by
     * @return the result of the multiplication of this by the other
     */
    public BigNumber multBigNumber(BigNumber other)
    {
        BigNumber result = new BigNumber();

        //Check if any of the factors are 0; if so return 0
        if (this.compareTo(result) == 0 || other.compareTo(result) == 0)
            return result;

        IntNode resultDigitNode = result._lastDigit;
        IntNode resultDigitNodeMarker = result._lastDigit;
        IntNode thisDigitNode = this._lastDigit;
        IntNode otherDigitNode = other._lastDigit;

        // Sum the product of every digit with every other digit
        while (thisDigitNode != null)
        {
            int thisDigit = thisDigitNode.getValue();
            int digit;
            int carry = 0;

            // Set the result digit node to the marker
            resultDigitNode = resultDigitNodeMarker;
            // Go over all of the other big number digits
            while(otherDigitNode != null)
            {
                int otherDigit = otherDigitNode.getValue();

                // Calculate the product of the digits
                int product = otherDigit * thisDigit + carry + resultDigitNode.getValue();

                // Calculate the right digit to put in the result
                digit = product % 10;

                // Calculate the carry to add in the next iteration
                carry = product / 10;

                // put the right digit in the result digit node
                resultDigitNode.setValue(digit);

                // If this is the last digit of both numbers and there is no carry  don't create a new node
                // nor advance to a new node
                if (thisDigitNode.getNext() == null && otherDigitNode.getNext() == null && carry == 0)
                    break;

                // Advance with the digit node
                otherDigitNode = otherDigitNode.getNext();

                // Check if the result big number has a next node; if not create it.
                if (resultDigitNode.getNext() == null)
                {
                    resultDigitNode.setNext(new IntNode(0,null));
                }
                resultDigitNode = resultDigitNode.getNext();
            }

            // If there is a carry left after the pass on other's digits, put the carry in the result
            while (carry != 0)
            {
                digit = carry % 10;
                carry /= 10;
                resultDigitNode.setValue(digit);

                // If there is no carry skip creating it and advancing
                /*if (carry == 0)
                    break;

                // Check if the result big number has a next node; if not create it
                if (resultDigitNode.getNext() == null)
                {
                    resultDigitNode.setNext(new IntNode(0,null));
                }
                resultDigitNode = resultDigitNode.getNext();*/
            }

            // Advance with the this digit node
            thisDigitNode = thisDigitNode.getNext();

            // Reset the other digit node
            otherDigitNode = other._lastDigit;

            // Advance with the result digit node marker
            resultDigitNodeMarker = resultDigitNodeMarker.getNext();
        }
        return result;
    }
}

