package system.statistic;

public class StopLog {

    private int idStop;
    private int idRoute;
    private int idMinibus;
    private int loadedPassenger;
    private int unloadedPassenger;
    private double loadingPercent;
    private int loadLevel;

    private String time;

    public StopLog(String time, int idStop, int idRoute, int idMinibus, int loadedPassenger, int unloadedPassenger, double loadingPercent, int loadLevel) {
        this.time = time;
        this.idStop = idStop;
        this.idRoute = idRoute;
        this.idMinibus = idMinibus;
        this.loadedPassenger = loadedPassenger;
        this.unloadedPassenger = unloadedPassenger;
        this.loadingPercent = Math.ceil(loadingPercent);
        this.loadLevel = loadLevel;

        DBUpdate object = new DBUpdate();
        object.update(this);
    }

    public int getIdStop() {
        return idStop;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public int getIdMinibus() {
        return idMinibus;
    }

    public int getLoadedPassenger() {
        return loadedPassenger;
    }

    public int getUnloadedPassenger() {
        return unloadedPassenger;
    }

    public Double getLoadingPercent() {
        return loadingPercent;
    }

    public String getTime() {
        return time;
    }

    public int getLoadLevel() {
        return loadLevel;
    }

    @Override
    public String toString() {
        return "time: " + time +
                ", idR: " + idRoute+
                ", idM: " + idMinibus +
                ", idRS: " + idStop +
                ", boarded: " + loadedPassenger +
                ", disembarked: " + unloadedPassenger +
                ", load level: " + loadLevel + " | " + loadingPercent + "%";
    }
}
