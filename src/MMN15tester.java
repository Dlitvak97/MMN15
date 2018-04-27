public class MMN15tester
{
    public static void main(String args[])
    {
        BigNumber a = new BigNumber(26);
        BigNumber b = new BigNumber(324);
        System.out.println(a);
        System.out.println(b);
        System.out.println(b.addBigNumber(a));
        System.out.println(a.addBigNumber(b));
        System.out.println(b.multiBigNumber(a));
        System.out.println(a.multiBigNumber(b));
        System.out.println(b.subtractBigNumber(a));
        System.out.println(a.subtractBigNumber(b));
        System.out.println(b.compareTo(a));
        System.out.println(a.compareTo(b));

    }
}
