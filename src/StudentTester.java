public class StudentTester {
    public static void main(String[] args) {
        BigNumber bigNumber1 = new BigNumber();
        BigNumber bigNumber2 = new BigNumber(1234567890);
        BigNumber bigNumber2Copy = new BigNumber(bigNumber2);
        BigNumber bigNumber3 = new BigNumber(645654464);

        boolean failed = false;
        if (!bigNumber2.toString().equals("1234567890")) {
            System.out.println("toString has failed");
            failed = true;
        }

        if (!bigNumber2.toString().equals(bigNumber2Copy.toString())) {
            System.out.println("toString has failed");
            failed = true;
        }

        if (failed) {
            System.out.println("Tester aborted due to toString failure. You need to fix your toString and then run the tester again");
        } else {
            if (!bigNumber2.addBigNumber(bigNumber2Copy).toString().equals("2469135780")) {
                System.out.println("addBigNumber has failed");
                failed = true;
            }

            if (!bigNumber2Copy.addLong(1342343424).toString().equals("2576911314")) {
                System.out.println("addLong has failed");
                failed = true;
            }

            if (!bigNumber2.subtractBigNumber(bigNumber3).toString().equals("588913426")) {
                System.out.println("subtractBigNumber has failed");
                failed = true;
            }

            if (!bigNumber3.subtractBigNumber(bigNumber2).toString().equals("588913426")) {
                System.out.println("subtractBigNumber has failed");
                failed = true;
            }

            if (!bigNumber2.subtractBigNumber(bigNumber2Copy).toString().equals("0")) {
                System.out.println("subtractBigNumber has failed");
                failed = true;
            }

            if (!bigNumber2.multBigNumber(bigNumber3).toString().equals("797104269289560960")) {
                System.out.println("multBigNumber has failed");
                failed = true;
            }

            if (!bigNumber3.multBigNumber(bigNumber2).toString().equals(bigNumber2.multBigNumber(bigNumber3).toString())) {
                System.out.println("multBigNumber has failed");
                failed = true;
            }

            if (!bigNumber2.multBigNumber(bigNumber1).toString().equals("0")) {
                System.out.println("multBigNumber has failed");
                failed = true;
            }

            if (bigNumber1.compareTo(bigNumber2) != -1) {
                System.out.println("compareTo has failed");
                failed = true;
            }

            if (bigNumber2.compareTo(bigNumber3) != 1) {
                System.out.println("compareTo has failed");
                failed = true;
            }

            if (bigNumber2.compareTo(bigNumber2Copy) != 0) {
                System.out.println("compareTo has failed");
                failed = true;
            }
        }

        if (!failed) {
            System.out.println("You have passed this tester. Please note that this is a basic tester and you should write your own tester.");
        }
    }
}
