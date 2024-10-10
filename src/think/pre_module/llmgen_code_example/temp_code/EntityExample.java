import java.util.Collection;

public class EntityExample {
    public EntityCollection entityCollection;

    public void processEntities() {
        Collection<Entity> entities = entityCollection.getEntities();
        // Use the entities collection for further processing
    }
}