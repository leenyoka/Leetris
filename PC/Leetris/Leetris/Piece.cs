using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Leetris
{
    public class Piece
    {
        string name;

        public string Name
        {
            get { return name; }
            set { name = value; }
        }
        string image;

        public string Image
        {
            get { return image; }
            set { image = value; }
        }
        List<State> states = new List<State>();

        public List<State> States
        {
            get { return states; }
            set { states = value; }
        }


        public Piece(string name, string image, List<State> states)
        {
            this.image = image;
            this.name = name;
            this.states = states;
        }
        public State GetNormalState()
        {
            State state = new State();
            foreach (State st in states)
            {
                if (st.Name.ToLower() == "normal")
                {
                    state = st;
                    break;
                }
            }
            return state;
        }
        public State GetNextState(int CurrentStateNum)
        {
            if (CurrentStateNum >= GetMaxState())
            {
                return GetState(1);
            }
            else
            {
                return GetState(CurrentStateNum + 1);
            }
        }
        public int GetMaxState()
        {
            int num = 0;
            foreach(State st in states)
            {
                if (st.Number > num)
                {
                    num = st.Number;
                }
            }
            return num;
        }
        public State GetState(int num)
        {
            State state = new State();
            foreach (State st in states)
            {
                if (st.Number == num)
                {
                    state = st;
                    break;
                }
            }
            return state;
        }

        public Piece()
        {

        }
        

        

        
    }
}
