using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Leetris
{
    public class State
    {
        string name;

        public string Name
        {
            get { return name; }
            set { name = value; }
        }
        int number;

        public int Number
        {
            get { return number; }
            set { number = value; }
        }
        List<Block> blocks = new List<Block>();

        public List<Block> Blocks
        {
            get { return blocks; }
            set { blocks = value; }
        }

        Block centerBlock = new Block(0, 0, true);

        public State(string Name, int number, List<Block> Blocks)
        {
            this.number = number;
            this.name = Name;
            foreach (Block bc in Blocks)
            {
                this.blocks.Add(bc);
            }

            blocks.Add(centerBlock);
        }
        public State()
        {
            blocks.Add(centerBlock);
        }
    }
}
