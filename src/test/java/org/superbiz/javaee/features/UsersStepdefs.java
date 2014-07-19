package org.superbiz.javaee.features;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Properties;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

public class UsersStepdefs {

	private final Properties props = Producer.getProperties();

	@Given("^I have the following users in the database:$")
	public void I_have_the_following_users_in_the_database(DataTable data)
			throws Throwable {

		final List<String> columns = data.getGherkinRows().get(0).getCells();
		System.out.println(Lists.newArrayList(columns
				.toArray(new String[columns.size()])));

		final List<String> values = data.getGherkinRows().get(1).getCells();

		Operation operation = sequenceOf(CommonDbOperations.DELETE_ALL,
				CommonDbOperations.INSERT_REFERENCE_DATA);
		for (int i = 1; i < values.size(); i++) {
			// Insert.Builder builder = Insert.into("users")
			// .columns(columns.toArray(new String[columns.size()]))
			// .values(data.getGherkinRows().get(i).getCells());

		}

		DbSetup dbSetup = new DbSetup(new DriverManagerDestination(
				props.getProperty("database.url"),
				props.getProperty("database.user"),
				props.getProperty("database.password")), operation);
		dbSetup.launch();
	}
}
