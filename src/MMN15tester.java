import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class MMN15tester
{

    public final static int NUM_OF_NUMBERS = 1000;


    static void toStringTest() {
        for (long i = 0; i < NUM_OF_NUMBERS*10000; i++) {
            assertEquals(""+i,new BigNumber(i).toString());
        }
        System.out.println("finished toStringTest");
    }


    static void addBigNumber() {
        for (int i = 0; i < NUM_OF_NUMBERS; i++) {
            BigNumber first = new BigNumber(i);
            for (int j = 0; j < NUM_OF_NUMBERS; j++) {
                BigNumber second = new BigNumber(j);
                assertEquals(first.addBigNumber(second).toString(),""+(i+j));
                assertEquals(second.addBigNumber(first).toString(),""+(j+i));
            }
        }

        for (int i = 0; i < NUM_OF_NUMBERS; i++) {
            BigNumber first = new BigNumber((long)i+ (long)Integer.MAX_VALUE);
            for (int j = 0; j < NUM_OF_NUMBERS; j++) {
                BigNumber second = new BigNumber((long)j+(long)Integer.MAX_VALUE);
                if ((""+(((long)i+ (long)Integer.MAX_VALUE)+((long)j+(long)Integer.MAX_VALUE))).equals("4294967295"))
                {
                    second = second;
                    BigNumber second2 = new BigNumber((long)j+(long)Integer.MAX_VALUE);
                }
                assertEquals(first.addBigNumber(second).toString(),""+(((long)i+ (long)Integer.MAX_VALUE)+((long)j+(long)Integer.MAX_VALUE)));
                assertEquals(second.addBigNumber(first).toString(),""+(((long)j+(long)Integer.MAX_VALUE)+((long)i+ (long)Integer.MAX_VALUE)));
            }
        }
        System.out.println("finished addBigNumber");
    }


    static void addLong() {
        for (long i = 0; i < NUM_OF_NUMBERS; i++) {
            BigNumber first = new BigNumber((long)i+ (long)Integer.MAX_VALUE);
            for (long j = 0; j < NUM_OF_NUMBERS; j++) {
                assertEquals(first.addLong(j).toString(),""+(i+ (long)Integer.MAX_VALUE+j));
            }
        }
        System.out.println("finished addLong");
    }


    static void subtractBigNumber() {
        for (long i = 0; i < NUM_OF_NUMBERS; i++) {
            BigNumber first = new BigNumber((long)i+ (long)Integer.MAX_VALUE);
            for (long j = 0; j < i; j++) {
                BigNumber second = new BigNumber((long)j+ (long)Integer.MAX_VALUE);
                if(!first.subtractBigNumber(second).toString().equals(""+(i-j)))
                    System.out.println(i+","+j);
                assertEquals(first.subtractBigNumber(second).toString(),""+((long)i+ (long)Integer.MAX_VALUE-((long)j+ (long)Integer.MAX_VALUE)));
                assertEquals(second.subtractBigNumber(first).toString(),""+((long)i+ (long)Integer.MAX_VALUE-((long)j+ (long)Integer.MAX_VALUE)));
            }
        }
        System.out.println("finished subtractBigNumber");
    }


    static void multBigNumber() {
        for (long i = 0; i < NUM_OF_NUMBERS; i++) {
            BigNumber first = new BigNumber((long)i+ (long)Integer.MAX_VALUE);
            for (long j = 0; j < NUM_OF_NUMBERS; j++) {
                BigNumber second = new BigNumber((long)j+ (long)Integer.MAX_VALUE);
                assertEquals(first.multBigNumber(second).toString(),""+(((long)j+ (long)Integer.MAX_VALUE)*((long)i+ (long)Integer.MAX_VALUE)));
                assertEquals(second.multBigNumber(first).toString(),""+(((long)j+ (long)Integer.MAX_VALUE)*((long)i+ (long)Integer.MAX_VALUE)));
            }
        }
        System.out.println("finished multBigNumber");
    }


    static void compareTo() {
        for (int i = 0; i < NUM_OF_NUMBERS; i++) {
            BigNumber first = new BigNumber((long)i+ (long)Integer.MAX_VALUE);
            for (int j = 0; j < NUM_OF_NUMBERS; j++) {
                BigNumber second = new BigNumber((long)j+ (long)Integer.MAX_VALUE);
                int result = (long)i+ (long)Integer.MAX_VALUE > (long)j+ (long)Integer.MAX_VALUE ? 1 : ((long)i+ (long)Integer.MAX_VALUE < (long)j+ (long)Integer.MAX_VALUE ? -1 : 0);
                assertEquals(result, first.compareTo(second));
                result = (long)j+ (long)Integer.MAX_VALUE > (long)i+ (long)Integer.MAX_VALUE ? 1 : ((long)j+ (long)Integer.MAX_VALUE < (long)i+ (long)Integer.MAX_VALUE ? -1 : 0);
                assertEquals(result, second.compareTo(first));
            }
        }
        System.out.println("finished compareTo");
    }

    public static void main(String args[])
    {
        toStringTest();
        addBigNumber();
        addLong();
        subtractBigNumber();
        multBigNumber();
        compareTo();


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
        String mul3;
        String mul4;

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
        for (long i = 0;i<=10000;i++)
        {
            String s1 = (new BigNumber(i)).toString();
            String s2 = new BigNumber(new BigNumber(i)).toString();
            if(!String.valueOf(i).equals(s1))
                System.out.println("0) Wrong with i");
            if(!String.valueOf(i).equals(s2))
                System.out.println("02) Wrong with i");
            for (long j = 0;j<=1000;j++)
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
        System.out.println("done");
    }
}
