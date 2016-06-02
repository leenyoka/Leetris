package com.example.linda.leetris;

import android.app.Activity;
import android.widget.*;

/**
 * Created by Linda on 2014/07/18.
 */
public class GridPiece extends ImageView {
    //#region Properties

    String symbol = "";

    int row;

    public int Row()
    {
        return row;
    }
    public void Row(int _row)
    {
         row = _row;
    }
    int col;

    public int Col()
    {
          return col;
    }
    public void Col(int _col)
    {
          col = _col;
    }

    int level;

    public int Level()
    {
        return level;
    }
    public void Level(int _level)
    {
        level = _level;
    }

    public GridPiece(int row, int col, int level, Activity activity)

    {
        super(activity.getBaseContext());
        this.row = row;
        this.col = col;
    }

    //#endregion Constructor

    //#region Methods

    public void Refresh(String pieceSymbol)
    {
        if (pieceSymbol.trim().toLowerCase()
                != this.symbol.trim())
        {
            this.symbol = pieceSymbol;

            if(pieceSymbol.toLowerCase() == "i")
            this.setImageResource(R.drawable.i);
            else if (pieceSymbol.toLowerCase() == "j")
                this.setImageResource(R.drawable.j);
            else if (pieceSymbol.toLowerCase() == "l")
                this.setImageResource(R.drawable.l);
            else if (pieceSymbol.toLowerCase() == "s")
                this.setImageResource(R.drawable.s);
            else if (pieceSymbol.toLowerCase() == "z")
                this.setImageResource(R.drawable.z);
            else if (pieceSymbol.toLowerCase() == "t")
                this.setImageResource(R.drawable.t);
            else if (pieceSymbol.toLowerCase() == "o")
                this.setImageResource(R.drawable.o);
        }
    }
    public void Refresh()
    {
        this.symbol = "";
        this.setImageResource(0);
    }
/*
    public ImageResource getMyImage(string choice)
    {
        ImageBrush bi = new ImageBrush();
        System.Uri uri = new Uri(@"../Images/" + choice + ".png", UriKind.Relative);

        if (uri == null)// what if its not null but has no image?
        {
            return null;
        }

        bi.ImageSource = new BitmapImage(uri);
        return bi;
    }

    public ImageBrush getMyPiece(string choice)
    {
        return getMyImage("Pieces/" + choice);
    }
    */
    //#endregion Methods
}
