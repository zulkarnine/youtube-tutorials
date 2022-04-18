package com.zulkarninecsedictionary;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryDatabaseHelper extends SQLiteOpenHelper {

	final static String DICTIONARY_DATABASE="dictionary";
	final static String ITEM_ID_COLUMN="id";
	final static String WORD_COLUMN="word";
	final static String DEFINITION_COLUMN="definition";
	
	
	final static String CREATE_DATABASE_QUERY="CREATE TABLE "+DICTIONARY_DATABASE+" ( "+
	ITEM_ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
	WORD_COLUMN+" TEXT , "+
	DEFINITION_COLUMN+" TEXT)";
	
	
	final static String ON_UPGRADE_QUERY="DROP TABLE "+DICTIONARY_DATABASE;
	
	Context context;
	
	
	public DictionaryDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, DICTIONARY_DATABASE, factory, version);
		this.context=context;
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
			database.execSQL(CREATE_DATABASE_QUERY);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL(ON_UPGRADE_QUERY);
		onCreate(database);
		
	}
	
	public long insertData(WordDefinition wordDefinition) {
		SQLiteDatabase database=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		
		values.put(WORD_COLUMN, wordDefinition.word);
		values.put(DEFINITION_COLUMN, wordDefinition.definition);
		
		return database.insert(DICTIONARY_DATABASE, null, values);		
		
	}

	public long updateData(WordDefinition wordDefinition) {
		SQLiteDatabase database=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		
		values.put(WORD_COLUMN, wordDefinition.word);
		values.put(DEFINITION_COLUMN, wordDefinition.definition);
		
		return database.update(DICTIONARY_DATABASE, values, WORD_COLUMN+" =?", new String[]{wordDefinition.word});	
		
	}
	
	public void deleteData(WordDefinition wordDefinition) {
		SQLiteDatabase database=this.getWritableDatabase();
		String queryString="DELETE FROM "+DICTIONARY_DATABASE+" WHERE "+WORD_COLUMN+" = '"+wordDefinition.word+"'";
		
		database.execSQL(queryString);
	}
	
	public ArrayList<WordDefinition> getAllWords() {
		ArrayList<WordDefinition> arrayList=new ArrayList<WordDefinition>();
		SQLiteDatabase database=this.getReadableDatabase();
		
		String selectAllQueryString="SELECT * FROM "+DICTIONARY_DATABASE;
		Cursor cursor=database.rawQuery(selectAllQueryString, null);
		
		if (cursor.moveToFirst()) {
			do {			
				WordDefinition wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));
				arrayList.add(wordDefinition);				
			} while (cursor.moveToNext());			
		}	
		return arrayList;
	}
	
	public WordDefinition getWordDefinition(String word) {
		SQLiteDatabase database=this.getReadableDatabase();
		WordDefinition wordDefinition=null;
		
		String selectQueryString="SELECT * FROM "+DICTIONARY_DATABASE+ " WHERE "+WORD_COLUMN+" = '"+word+ "'";
		Cursor cursor=database.rawQuery(selectQueryString, null);
		
		if (cursor.moveToFirst()) {
			wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));
			
		}	
		
		return wordDefinition;
		
	}
	
	public WordDefinition getWordDefinition(long id) {
		SQLiteDatabase database=this.getReadableDatabase();
		WordDefinition wordDefinition=null;
		
		String selectQueryString="SELECT * FROM "+DICTIONARY_DATABASE+ " WHERE "+ITEM_ID_COLUMN+" = '"+id+ "'";
		Cursor cursor=database.rawQuery(selectQueryString, null);
		
		if (cursor.moveToFirst()) {
			wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));
			
		}	
		
		return wordDefinition;
		
	}
	
	public void initializeDatabaseFortheFirstTime(ArrayList<WordDefinition> wordDefinitions) {
		SQLiteDatabase database=this.getWritableDatabase();
		database.execSQL("BEGIN");
		
		ContentValues contentValues=new ContentValues();
		
		for (WordDefinition wordDefinition : wordDefinitions) {
			contentValues.put(WORD_COLUMN, wordDefinition.word);
			contentValues.put(DEFINITION_COLUMN, wordDefinition.definition);			
			database.insert(DICTIONARY_DATABASE, null, contentValues);
		}
		database.execSQL("COMMIT");
		
	}

	
}
