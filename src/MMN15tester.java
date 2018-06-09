import java.math.*;
public class MMN15tester
{
    public static void main(String args[])
    {
        BigNumber a = new BigNumber(26);
        BigNumber b = new BigNumber(324);
        BigNumber c = new BigNumber(26);
        String sum1;
        String sum2;
        String sum3;
        String sum4;
        String sub1;
        String sub2;
        String mul1;
        String mul2;

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
        for (long i = 0;i<=1500;i++)
        {
            for (long j = 0;j<=1500;j++)
            {
                sum1=(new BigNumber(i).addBigNumber(new BigNumber(j))).toString();
                sum2=(new BigNumber(j).addBigNumber(new BigNumber(i))).toString();
                sum3=(new BigNumber(i).addLong((long)j)).toString();
                sum4=(new BigNumber(j).addLong((long)i)).toString();
                sub1=(new BigNumber(i).subtractBigNumber(new BigNumber(j))).toString();
                sub2=(new BigNumber(j).subtractBigNumber(new BigNumber(i))).toString();
                mul1=(new BigNumber(i).multBigNumber(new BigNumber(j))).toString();
                mul2=(new BigNumber(j).multBigNumber(new BigNumber(i))).toString();

                if(!String.valueOf(i + j).equals(sum1))
                    System.out.println("1) Wrong with i: "+i+" j: "+j);
                if(!String.valueOf(i + j).equals(sum2))
                    System.out.println("2) Wrong with i: "+i+" j: "+j);
                if(!String.valueOf(i + j).equals(sum3))
                    System.out.println("3) Wrong with i: "+i+" j: "+j);
                if(!String.valueOf(i + j).equals(sum4))
                    System.out.println("4) Wrong with i: "+i+" j: "+j);
                if(!String.valueOf(Math.abs(i - j)).equals(sub1))
                    System.out.println("5) Wrong with i: "+i+" j: "+j);
                if(!String.valueOf(Math.abs(i - j)).equals(sub2))
                    System.out.println("6) Wrong with i: "+i+" j: "+j);
                if(!String.valueOf(i * j).equals(mul1))
                    System.out.println("7) Wrong with i: "+i+" j: "+j);
                if(!String.valueOf(i * j).equals(mul2))
                    System.out.println("8) Wrong with i: "+i+" j: "+j);
            }
        }
    }
}
