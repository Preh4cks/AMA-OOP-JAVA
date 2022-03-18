package lab4;

public class Operator_Precedence {
    public static void main(String[] args) {
        /* Operator Precedence */
        String order1 = "a/b^c^d-e+f-g*h+i";
        String order2 = "3*10*2/15-2+4^2^2";
        String order3 = "r^s*t/u-v+w^x-y++";
        System.out.println("((a/b)^c)^((d-(e+(f-(g*h)))+i))");
        System.out.println("((((((3*10)*2)/15)-2)+4)^2)^2");
        System.out.println("(r^((((((s*t)/u)-v)+w))^(x-(y++))");
    }
}
