package org.superbiz.javaee.features;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import gherkin.formatter.model.DataTableRow;
import gherkin.formatter.model.Row;

import java.util.List;
import java.util.Properties;

import static com.google.common.collect.Lists.newArrayList;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

public class UsersSteps {

	private final Properties props = Producer.getProperties();

	@Given("^I have the following users in the database:$")
	public void I_have_the_following_users_in_the_database(DataTable data)
			throws Throwable {

		List<DataTableRow> rows = data.getGherkinRows();
		final List<String> columns = rows.get(0).getCells();
		System.out.println(columns);

		final List<DataTableRow> rows2 = rows.subList(1, rows.size());

		List<Operation> operations = newArrayList();
		operations.add(CommonDbOperations.DELETE_ALL);
		// operations.add(CommonDbOperations.INSERT_REFERENCE_DATA);
		Insert.Builder builder = Insert.into("users");
		builder.columns(columns.toArray(new String[columns.size()]));
		for (Row row : rows2) {
			System.out.println(row.getCells());
			builder.values(row.getCells().toArray(
					new String[row.getCells().size()]));
			operations.add(builder.build());
		}

		Operation allOperations = sequenceOf(operations);

		DbSetup dbSetup = new DbSetup(new DriverManagerDestination(
				props.getProperty("database.url"),
				props.getProperty("database.user"),
				props.getProperty("database.password")), allOperations);
		dbSetup.launch();
	}
}
