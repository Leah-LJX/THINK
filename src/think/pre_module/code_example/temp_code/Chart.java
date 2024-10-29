import com.example.EntityCollection;
import com.example.ChartEntity;

public class Chart {
    public EntityCollection entityCollection;

    public Chart(EntityCollection entityCollection) {
        this.entityCollection = entityCollection;
    }

    public void exampleUsage() {
        double x = 10.5;
        double y = 8.2;

        ChartEntity entity = entityCollection.getEntity(x, y);
        // Use the entity as needed
    }
}