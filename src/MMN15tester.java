public class MMN15tester
{
    public static void main(String args[])
    {
        BigNumber a = new BigNumber(26);
        BigNumber b = new BigNumber(324);
        BigNumber c = new BigNumber(26);
        BigNumber copy = new BigNumber(c);
        System.out.println(a + " should be 26");
        System.out.println(b+ " should be 324");
        System.out.println(b.addBigNumber(a)+ " should be 350");
        System.out.println(a.addBigNumber(b)+ " should be 350");
        System.out.println(b.multBigNumber(a)+ " should be 8424");
        System.out.println(a.multBigNumber(b)+ " should be 8424");
        System.out.println(b.subtractBigNumber(a)+ " should be 298");
        System.out.println(a.subtractBigNumber(b)+ " should be 298");
        System.out.println(b.compareTo(a)+ " should be 1");
        System.out.println(a.compareTo(b)+ " should be -1");
        System.out.println(a.compareTo(c)+ " should be 0");
        System.out.println(c.compareTo(a)+ " should be 0");
        System.out.println(c.compareTo(c)+ " should be 0");
    }
}
