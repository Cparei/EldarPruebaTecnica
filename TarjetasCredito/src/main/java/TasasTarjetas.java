import java.util.Calendar;

public class TasasTarjetas {

    Calendar calendar = Calendar.getInstance();
    public double tasaVisa(){
        return (calendar.get(Calendar.YEAR) / 12) / 100;
    }
    public double tasaNara(){
        return (calendar.get(Calendar.DAY_OF_MONTH) * 0.5) / 100;
    }
    public double tasaAmex(){
        return (calendar.get(Calendar.MONTH) * 0.1) / 100;
    }

    public double aplicarTopes(double tasaCalculada){
        double tasaTopeMin = 0.003;
        double tasaTopeMax = 0.05;
        if (tasaCalculada < tasaTopeMin) return tasaTopeMin;
        return Math.min(tasaCalculada, tasaTopeMax);
    }
}
