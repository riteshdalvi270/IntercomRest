package intercom.buildrelationship.server.assistant.json;

import intercom.buildrelationship.exception.Verifier;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Assistant to assist with reading JSON data.
 * @author Ritesh Dalvi.
 */
public class JSONAssistant {

    /**
     * Retrieves the latitude information from the Json Object.
     * @param jsonObject The {@link JSONObject} (cannot be null).
     * @param latitude The key to be used to retrieve from the {@link JSONObject} (cannot be null,empty or blank).
     * @return non-null {@link String} representing the latitude.
     */
    public static String getLatitude(final JSONObject jsonObject, final String latitude) {
        return getSafeJSONObject(jsonObject,latitude);
    }

    /**
     * Retrieves the user id information from the Json Object.
     * @param jsonObject The {@link JSONObject} (cannot be null).
     * @param userId The key to be used to retrieve from the {@link JSONObject} (cannot be null,empty or blank).
     * @return non-null {@link String} representing the user id.
     */
    public static String getUserId(final JSONObject jsonObject, final String userId) {
        return getSafeJSONObject(jsonObject,userId);
    }

    /**
     * Retrieves the name information from the Json Object.
     * @param jsonObject The {@link JSONObject} (cannot be null).
     * @param name The key to be used to retrieve from the {@link JSONObject} (cannot be null,empty or blank).
     * @return non-null {@link String} representing the name.
     */
    public static String getName(final JSONObject jsonObject, final String name) {
        return getSafeJSONObject(jsonObject,name);
    }

    /**
     * Retrieves the longitude information from the Json Object.
     * @param jsonObject The {@link JSONObject} (cannot be null).
     * @param longitude The key to be used to retrieve from the {@link JSONObject} (cannot be null,empty or blank).
     * @return non-null {@link String} representing the longitude.
     */
    public static String getLongitude(final JSONObject jsonObject, final String longitude) {
        return getSafeJSONObject(jsonObject,longitude);
    }

    /**
     * Retrieves the Json String safely from {@link JSONObject} by performing appropriate checks.
     * @param jsonObject The {@link JSONObject} (cannot be null).
     * @param key The key to be used to retrieve from the {@link JSONObject} (cannot be null,empty or blank).
     * @return non-null {@link String} representing the Json string.
     * @throw {@link intercom.buildrelationship.exception.VerifyException} if {@link JSONObject} or key is null or empty. {@link RuntimeException} if fails to retrieve json string using the key provided
     */
    private static String getSafeJSONObject(final JSONObject jsonObject, final String key) {
        Verifier.verifyNotNull(jsonObject, "jsonObject:null");
        Verifier.verifyBlank(key, "key:null,empty or blank");

        if(jsonObject.has(key)) {
            return jsonObject.optString(key);
        }else {
            throw new RuntimeException("Failed to retrieve json string from the json object using key");
        }
    }
}
