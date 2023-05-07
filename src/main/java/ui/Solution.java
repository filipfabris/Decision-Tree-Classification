package ui;

import ui.algorithm.ID3;
import ui.model.DataInput;
import ui.util.ParseInput;

import java.io.IOException;
import java.util.Locale;

public class Solution {

    public static void main(String[] args) throws IOException {

        Locale.setDefault( Locale.US );

//        DataInput dataInput = ParseInput.parseInput( "volleyball.csv" );
//
//        ID3 id3 = new ID3(1);
//        id3.fit( dataInput );
//
//        DataInput dataInputTest = ParseInput.parseInput( "test.csv" );
//
//        id3.predict( dataInputTest );

        int maxDepth = Integer.MAX_VALUE;
        if(args.length == 3){
            maxDepth = Integer.parseInt( args[2] );
        }

        DataInput dataInput = ParseInput.parseInput( args[0] );
        DataInput dataInputTest = ParseInput.parseInput( args[1] );

        ID3 id3 = new ID3(maxDepth);
        id3.fit( dataInput );
        id3.predict( dataInputTest );



    }
}