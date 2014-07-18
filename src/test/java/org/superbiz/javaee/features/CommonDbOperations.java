package org.superbiz.javaee.features;

import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.*;

public class CommonDbOperations {
	public static final Operation DELETE_ALL = deleteAllFrom("users");

	public static final Operation INSERT_REFERENCE_DATA = sequenceOf(insertInto(
			"users")
			.columns("id", "created", "modified", "email", "fullname",
					"password", "version")
			.values(1L, "2014-07-17", null, "cchacin@superbiz.org", "Carlos",
					"passw0rd", 0)
			// .values(2L, "2014-07-17", null, "cchacin2@superbiz.org",
			// "Carlos 2", "passw0rd", 0)
			.build());
}
