using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Leetris
{
    public class Block
    {
        int y;

        public int Y
        {
            get { return y; }
            set { y = value; }
        }
        int x;

        public int X
        {
            get { return x; }
            set { x = value; }
        }

        public Block(int y, int x)
        {
            this.x = x;
            this.y = y;
        }
        public Block(int y, int x, bool center)
        {
            this.x = x;
            this.y = y;
            this.center = center;
        }
        bool center = false;

        public bool Center
        {
            get { return center; }
            set { center = value; }
        }

        
    }
}
