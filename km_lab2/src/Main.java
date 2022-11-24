public class Main {
    public static double func(double x,double y)
    {
        return 2*y+ Math.sqrt(x);
    }
    public static double Runge(double y,double x,double h)
    {
    double k1=h*func(x,y);
    double k2=h*func(x+h,y+k1);
    return y+(1/2.)*(k1+k2);
    }
    public static double implicitAdams(double h,double y,double derivativeY,double derivativeY1,double derivativeY2,double derivativeY3)
    {
        double delta= h*(derivativeY+1/2.*derivativeY1-1/12.*derivativeY2-1/24.*derivativeY3);
        return y+delta;
    }
    public static void main(String[] args)
    {
     double h=0.1;
     double y=2.0;
     double x=1.0;
     double[] result=new double[11];
     result[0]=y;
     System.out.println("Runge");
     System.out.println("Result: x= "+(x)+", y="+y);
     for(int i=11;i<14;i++){
         System.out.println("Result: x= "+String.format("%.4f",(x+h))+", y="+String.format("%.4f",Runge(y,x,h)));
         y=Runge(y,x,h);
         result[i-10]=y;
         x+=h;
     }
     double[] arrayfunc=new double[11];
        double x1=x;
     for(int i=0;i<4;i++)
     {
         arrayfunc[i]=func(x1,result[i]);
         x1+=h;
     }
        double[] array1=new double[10];
        for(int i=0;i<3;i++)
        {

            array1[i]=arrayfunc[i+1]-arrayfunc[i];
        }

        double[] array2=new double[9];
        for(int i=0;i<2;i++)
        {
            array2[i]=array1[i+1]-array1[i];
        }
        double[] array3=new double[8];
        for(int i=0;i<1;i++)
        {
            array3[i]=array2[i+1]-array2[i];
        }
        for(int i=4;i<11;i++)
        {
            result[i]=implicitAdams(h,result[i-1],arrayfunc[i-1],array1[i-2],array2[i-3],array3[i-4]);
            x+=h;
            System.out.println("Result: x= "+String.format("%.4f",x)+", y= "+String.format("%.4f",result[i]));
            arrayfunc[i]=func(x,result[i]);
            array1[i-1]=arrayfunc[i]-arrayfunc[i-1];
            array2[i-2]=array1[i-1]-array1[i-2];
            array3[i-3]=array2[i-2]-array2[i-3];
        }

    }
}
