package persistence;

import org.json.JSONObject;

public interface Writable {

    // EFFECTS: returns as JSON object
    JSONObject toJson();
}
