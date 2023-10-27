package com.org.challange4.util;

import java.util.UUID;

public class LongConverterUUID {
    public static UUID convertLongToUUID(long longValue) {
        long mostSigBits = longValue;
        long leastSigBits = 0; // Set the least significant bits to 0

        return new UUID(mostSigBits, leastSigBits);
    }
}
