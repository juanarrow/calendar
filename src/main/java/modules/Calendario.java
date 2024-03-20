package modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Calendario {

    private Date fecha;
    private int mes;
    private int anio;

    private String obtenerMes(Date fecha){
        Locale espanol = new Locale("es", "ES");
        SimpleDateFormat formatoMes = new SimpleDateFormat("MMMM", espanol);
        String nombreMes = formatoMes.format(fecha);
        return nombreMes;
    }

    private String obtenerAnio(Date fecha){
        Locale espanol = new Locale("es", "ES");
        SimpleDateFormat formatoAnio = new SimpleDateFormat("YYYY", espanol);
        String anio = formatoAnio.format(fecha);
        return anio;
    }

    public Calendario(){
        this.fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        this.anio = cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH) + 1;
    }

    public Date siguienteMes(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, 1);
        this.fecha = cal.getTime();
        this.anio = cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH)+1;
        return this.fecha;
    }

    public Date anteriorMes(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, -1);
        this.fecha = cal.getTime();
        this.anio = cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH)+1;
        return this.fecha;
    }

    /** setFecha
     * Cambia la fecha actual del calendario
     * @param fecha Fecha en el formato YYYY-MM
     */
    public void setFecha(String fecha) {
        String datos[] = fecha.split("-");
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(datos[0]), Integer.parseInt(datos[1])-1,1,0,0);
        this.fecha = cal.getTime();
        this.anio=cal.get(Calendar.YEAR);
        this.mes=cal.get(Calendar.MONTH);
    }

    public String getFechaActual() {
        return String.format("%s-%s", anio, mes);
    }

    public String getFechaHoy() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return String.format("%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
    }

    public String getFechaAnt() {
        Calendar cal = Calendar.getInstance(Locale.getDefault()); // Obtén una instancia de Calendar con la configuración regional predeterminada
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, -1);
        return String.format("%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
    }

    

    public String getFechaSig() {
        Calendar cal = Calendar.getInstance(Locale.getDefault()); // Obtén una instancia de Calendar con la configuración regional predeterminada
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, 1);
        return String.format("%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
    }

    public String getMes(Date fecha) {
        return obtenerMes(fecha);
    }

    public String getAnio(Date fecha) {
        return obtenerAnio(fecha);
    }

    private String getDays(){
        
        Calendar cal = Calendar.getInstance(); // Obtén una instancia de Calendar con la configuración regional predeterminada

        cal.setTime(fecha); // Establece la fecha de tu interés

        // Ajusta la fecha al primer día del mes y reinicia la hora, minutos, segundos y milisegundos
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int adjustedDayOfWeek = dayOfWeek - 1; // Ajustar para que el valor empiece en 0
        if (adjustedDayOfWeek == 0) adjustedDayOfWeek = 7; // Hacer que el domingo sea 7

        for(int i= 1; i<adjustedDayOfWeek; i++)
            cal.add(Calendar.DAY_OF_MONTH, -1);
        String days = "";
        Calendar calTemp = Calendar.getInstance();
        calTemp.setTime(new Date());
        for(int i=0; i < 42; i++){
            boolean isToday = false;
            isToday = calTemp.get(Calendar.YEAR)==cal.get(Calendar.YEAR) &&
                      calTemp.get(Calendar.MONTH)==cal.get(Calendar.MONTH) &&
                      calTemp.get(Calendar.DAY_OF_MONTH)==cal.get(Calendar.DAY_OF_MONTH);
            boolean isWeekend = cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7;
            days+=String.format("<div class=\"day %s %s\">%s</div>",(isToday?"today":""), (isWeekend?"weekend":""), cal.get(Calendar.DAY_OF_MONTH));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;        
    }

    @Override
    public String toString() {
        
        return String.format(
                      "<div class=\"calendar\">"+
                       "<div class=\"calendar-header\">"+
                        "<div class=\"btn-group\" role=\"group\">"+
                            "<form method=\"POST\" action=\"index.jsp\">"+
                                "<input type=\"hidden\" name=\"fecha\" value=\"%s\">"+
                                "<button type=\"submit\" class=\"btn btn-secondary rounded-left-only\">"+
                                    "<span aria-hidden=\"true\">&larr;</span>"+
                                "</button>"+
                            "</form>"+
                            "<form method=\"POST\" action=\"index.jsp\">"+
                                "<input type=\"hidden\" name=\"fecha\" value=\"%s\">"+
                                "<button type=\"submit\" class=\"btn btn-secondary rounded-right-only\">"+
                                    "<span aria-hidden=\"true\">&rarr;</span>"+
                                "</button>"+
                            "</form>"+
                            "<form method=\"POST\" action=\"index.jsp\">"+
                                "<input type=\"hidden\" name=\"fecha\" value=\"%s\">"+
                                "<button type=\"submit\" class=\"ml-2 btn btn-secondary\">"+
                                    "<span aria-hidden=\"true\">Hoy</span>"+
                                "</button>"+
                            "</form>"+
                            
                        "</div>"+
                        "<h2>%s %s</h2>"+
                       "</div>"+
                       "<div class=\"bg-primary text-white weekdays\">"+
                         "<div>Lunes</div>"+
                         "<div>Martes</div>"+
                         "<div>Miércoles</div>"+
                         "<div>Jueves</div>"+
                         "<div>Viernes</div>"+
                         "<div>Sábado</div>"+
                         "<div>Domingo</div>"+
                       "</div>"+
                       "<div class=\"days\">"+
                       "%s"+
                      "</div>", getFechaAnt(), getFechaSig(), getFechaHoy(), getMes(fecha), getAnio(fecha), getDays());
    }
}
