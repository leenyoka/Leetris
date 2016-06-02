using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Controls;

namespace Leetris
{
    public class GridBlock: Label
    {
        bool isFree;

        public bool IsFree
        {
            get { return isFree; }
            set { isFree = value; }
        }

        int x;

        public int X
        {
            get { return x; }
            set { x = value; }
        }

        int y;

        public int Y
        {
            get { return y; }
            set { y = value; }
        }
        public GridBlock()
        {
            isFree = true;
        }
    }
}
