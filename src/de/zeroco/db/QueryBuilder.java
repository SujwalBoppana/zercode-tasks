package de.zeroco.db;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryBuilder {
		public static final String GRAVE = "`";
		public static void main(String[] args) {
			List<String> columns = new ArrayList<>(Arrays.asList("rollno", "name", "age", "email", "phno", "gender"));
			String schema = "zerocode";
		//	String tableName = "student";
			System.out.println(getInsertQuery("zerocode", "student", columns));
			System.out.println(getUpdateQuery("zerocode", "student", columns, "column"));
////			System.out.println(getAllDataQuery("zerocode", "student", columns));
//			System.out.println(getDataQuery(schema, tableName, columns, "name", "=","name"));
//			System.out.println(getDeleteQuery("zerocode", "student", "student","IN", columns));
//			System.out.println(getDeleteColumnQuery(schema, "employes", "gender"));
			// List<String> columns = new ArrayList<>(Arrays.asList("rollno","branch",
			// "subject", "subject_code"));
		//	List<String> dataTypes = new ArrayList<>(Arrays.asList("VARCHAR(5)", "VARCHAR(20)", "VARCHAR(25)"));
	//		List<String> constrains = new ArrayList<>(Arrays.asList("NOT NULL", "UNIQUE", "UNIQUE"));

			// String tableName = "subject_details";
			// System.out.println(getCreateTableQuery(schema, tableName, columns, dataTypes,
			// constrains));
			// System.out.println(getDeleteColumnQuery(schema, tableName, "name"));
			// System.out.println(getAddColumnQuery(schema, "employee", "pk_id",
			// "int","primary key"));
//				System.out.println(getCrossJoinQuery(schema, "student_details", "subject_details", columns));
		//	List<String> secondTableColumns = new ArrayList<>();
			List<String> firstTableColumns = new ArrayList<>(Arrays.asList("data_set","source_column"));
			System.out.println(getInsertQuery(schema, "app_migration", firstTableColumns));
			//System.out.println(getJoinsQuery(schema, "employee", "department",  firstTableColumns, secondTableColumns, "LEFT JOIN", "DEPTCODE", "DEPTCODE"));
		}
		
		/**
		 * this method will return a  insert  query 
		 * @author sujwal b
		 * @since 2022-12-22
		 * @param schema
		 * @param tableName
		 * @param columns
		 * @return query
		 */
		public static String getInsertQuery(String schema, String tableName, List<String> columns) {
			String col = "";
			String placeHolders = "";
			for (String column : columns) {
				col += "," + GRAVE + column + GRAVE;
				placeHolders += ",?";
			}
			return "INSERT INTO " + GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE + " (" + col.substring(1) + ")  VALUES (" + placeHolders.substring(1) + ");";
		}
		/**
		 * this method will returns the query to get the data in the given table
		 * @author sujwal b
		 * @since 2022-12-22
		 * @param schema
		 * @param tableName
		 * @param columns
		 * @return query
		 */
		public static String getAllQuery(String schema, String tableName, List<String> columns) {
			String col = "";
			for (String column : columns) {
				col += ", "+GRAVE+column+GRAVE;
			}
			return "SELECT " + (Utility.isBlankWithVarArguments(columns) ? " * ":col.substring(1))+ " FROM "+ GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE +";" ;
		}
		/**
		 * this method will returns a query 
		 * @param schema
		 * @param tableName
		 * @param columns
		 * @param referenceColmn
		 * @param operator
		 * @param referenceData
		 * @return
		 */
		public static String getDataQuery(String schema, String tableName, List<String> columns,String referenceColmn,String operator,String referenceData) {
			String col = "";
			for (String column : columns) {
				col += ", "+GRAVE+column+GRAVE;
			}
			return "SELECT " +(Utility.isBlankWithVarArguments(columns) ? " * ":col.substring(1))+ " FROM "+ GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE +" WHERE ( `"+referenceColmn+"` "+operator+" (\""+ referenceData +"\"));" ;
		}
		/**
		 * this method will return a query to update the data with respect to the condition
		 * @author sujwal b
		 * @since 2022-12-22
		 * @param schema
		 * @param tableName
		 * @param columns
		 * @param referenceColmn
		 * @return query
		 */
		public static String getUpdateQuery(String schema, String tableName, List<String> columns,String referenceColmn ) {
			String col = "";
			for (String column : columns) {
				col += ","+GRAVE+column+GRAVE+" = ? ";
			}
			return "UPDATE " + GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE + " SET " + col.substring(1) + " WHERE (" +GRAVE+ referenceColmn+ GRAVE+" = ? );";
		}
		
		/**
		 * this method will return the query to delete the data
		 * @author sujwal b
		 * @since 2022-12-23
		 * @param schema
		 * @param tableName
		 * @param referenceColumn
		 * @param referenceData
		 * @return query
		 */
		public static String getDeleteQuery(String schema, String tableName,String referenceColumn,String operator,List<Object> referenceData) {
			String refdata = "";
			for (Object data : referenceData) {
				refdata+=","+data;
			}
			return "DELETE FROM "+GRAVE+schema+GRAVE+"."+GRAVE+tableName+GRAVE+" WHERE ("+GRAVE+referenceColumn+GRAVE+" "+operator+" (\"" + refdata.substring(1)+"\"));";
		}
		
		/**
		 * this method will returns the query to delete a column
		 * @author sujwal b
		 * @since 2022-12-23
		 * @param schema
		 * @param tableName
		 * @param columnName
		 * @return query
		 */
		public static String getDropQuery(String schema, String tableName,String columnName) {
			return "ALTER TABLE "+GRAVE+schema+GRAVE+"."+GRAVE+tableName+GRAVE+" DROP "+GRAVE+columnName+GRAVE+";";
			
		}
		/**
		 * this method will returns a query to add a column
		 * @author sujwal b
		 * @since 2022-12-23
		 * @param schema
		 * @param tableName
		 * @param columnName
		 * @param dataTypeAndConstrains
		 * @return query
		 */
		public static String getAddColumnQuery(String schema, String tableName,String columnName,String... dataTypeAndConstrains) {
			String col = "";
			for (String data : dataTypeAndConstrains) {
				col += data+" ";
			}
			return "ALTER TABLE "+GRAVE+schema+GRAVE+"."+GRAVE+tableName+GRAVE+" ADD "+GRAVE+columnName+GRAVE+" "+col+"; " ;
			
		}
		
		/**
		 * this method will return a query to create a table
		 * @author sujwal b
		 * @since 2022-12-26
		 * @param schema
		 * @param tableName
		 * @param columns
		 * @param dataTypes
		 * @param constrains
		 * @return query
		 */
		public static String getCreateTableQuery(String schema, String tableName, List<String> columns,
				List<String> dataTypes, List<String> constrains) {
			String col = "";
			for (int i = 0; i < columns.size(); i++) {
				col += "," + GRAVE+columns.get(i)+GRAVE + " " + dataTypes.get(i) + " " + constrains.get(i);
			}
			return "CREATE TABLE "+tableName+"("+col.substring(1)+");";
		}
		
		/**
		 * this method will returns a query to cross join two tables
		 * @author sujwal b
		 * @since 2022-12-26
		 * @param schema
		 * @param firstTable
		 * @param secondTable
		 * @param columnsOfFirstTable
		 * @param columnsOfSecondTable
		 * @param jointType
		 * @return query
		 */
		public static String getCrossJoinQuery(String schema, String firstTable, String secondTable,
				List<String> columnsOfFirstTable, List<String> columnsOfSecondTable,String jointType) {
			String col = "";
			for (String column : columnsOfFirstTable)
				col += "," + GRAVE + firstTable + GRAVE + "." + GRAVE + column + GRAVE;
			for (String column : columnsOfSecondTable)
				col += "," + GRAVE + secondTable + GRAVE + "." + GRAVE + column + GRAVE;
			return "SELECT "
					+ (Utility.isBlankWithVarArguments(columnsOfFirstTable, columnsOfSecondTable) ? " * "
							: col.substring(1))
					+ " FROM " + GRAVE + firstTable + GRAVE + " "+jointType+" JOIN " + GRAVE + secondTable + GRAVE + ";";
		}

		/***
		 * this method will returns a query to inner join two tables
		 * @author sujwal
		 * @since 2022-12-26
		 * @param schema
		 * @param firstTable
		 * @param secondTable
		 * @param columnsOfFirstTable
		 * @param columnsOfSecondTable
		 * @param tableOneRef
		 * @param tableTwoRef
		 * @return query
		 */
		public static String getJoinsQuery(String schema, String firstTable, String secondTable,
				List<String> columnsOfFirstTable, List<String> columnsOfSecondTable,String jointType, String tableOneRef,
				String tableTwoRef) {
			String col = "";
			for (String column : columnsOfFirstTable)
				col += "," + GRAVE + firstTable + GRAVE + "." + GRAVE + column + GRAVE;
			for (String column : columnsOfSecondTable)
				col += "," + GRAVE + secondTable + GRAVE + "." + GRAVE + column + GRAVE;
			return "SELECT "
					+ (Utility.isBlankWithVarArguments(columnsOfFirstTable, columnsOfSecondTable) ? " * "
							: col.substring(1))
					+ " FROM " + GRAVE + firstTable + GRAVE + " "+jointType +" " + GRAVE + secondTable + GRAVE + " ON " + GRAVE
					+ firstTable + GRAVE + "." + GRAVE + tableOneRef + GRAVE + " = " + GRAVE + secondTable + GRAVE + "."
					+ GRAVE + tableTwoRef + GRAVE + ";";
		}



		

	}
