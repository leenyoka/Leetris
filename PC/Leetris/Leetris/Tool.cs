using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Threading;

namespace Leetris
{
    public class Tool
    {
        int points = 0;

        public int Points
        {
            get { return points; }
            set { points = value; }
        }
        bool started = false;

        public bool Started
        {
            get { return started; }
            set { started = value; }
        }

        DispatcherTimer countdown;//associated with speed

        public DispatcherTimer Countdown
        {
            get { return countdown; }
            set { countdown = value; }
        }

        DispatcherTimer flashPlayer;

        public DispatcherTimer FlashPlayer
        {
            get { return flashPlayer; }
            set { flashPlayer = value; }
        }

        DispatcherTimer timeKeeper;

        public DispatcherTimer TimeKeeper
        {
            get { return timeKeeper; }
            set { timeKeeper = value; }
        }

        int elapsedSeconds;

        public int ElapsedSeconds
        {
            get { return elapsedSeconds; }
            set { elapsedSeconds = value; }
        }

        int timeMilestone = 10000;

        public int TimeMilestone
        {
            get { return timeMilestone; }
            set { timeMilestone = value; }
        }

        List<Piece> pieces = new List<Piece>();

        #region Current Piece

        Piece currentPiece;

        int left = 0;

        public int Left
        {
            get { return left; }
            set { left = value; }
        }
        int right = 0;

        public int Right
        {
            get { return right; }
            set { right = value; }
        }
        int down = 0;

        public int Down
        {
            get { return down; }
            set { down = value; }
        }

        public Piece CurrentPiece
        {
            get { return currentPiece; }
            set { currentPiece = value; }
        }

        #endregion Current Piece

        List<Block> currentBlocks = new List<Block>();
        int currentStateNumber;

        State currentState = new State();

        public State CurrentState
        {
            get { return currentState; }
            set { currentState = value; }
        }

        public int CurrentStateNumber
        {
            get { return currentStateNumber; }
            set { currentStateNumber = value; }
        }

        public List<Block> CurrentBlocks
        {
            get { return currentBlocks; }
            set { currentBlocks = value; }
        }

        public List<Piece> Pieces
        {
            get { return pieces; }
            set { pieces = value; }
        }

        public Tool()
        {
            InitiatePieces();
        }
        public Piece GetPiece(string name)
        {
            if ("Square" == name)
            {
                return InitiateSqaure();
            }
            else if ("Zright" == name)
            {
                return InitiateZRight();
            }
            else if ("Zleft" == name)
            {
                return InitiateZLeft();
            }
            else if ("Lright" == name)
            {
                return InitiateLright();
            }
            else if ("Lleft" == name)
            {
                return InitiateLleft();
            }
            else if ("Triangle" == name)
            {
                return InitiateTriangle();
            }
            return InitiateStraight();
        }


        public void InitiatePieces()
        {
            this.pieces.Add(InitiateSqaure());
            this.pieces.Add(InitiateZRight());
            this.pieces.Add(InitiateZLeft());
            this.pieces.Add(InitiateStraight());
            this.pieces.Add(InitiateTriangle());
            this.pieces.Add(InitiateLleft());
            this.pieces.Add(InitiateLright());
            //add other pieces
        }
        public Piece InitiateSqaure()
        {
            State state;
            List<Block> blocks = new List<Block>();

            Block a = new Block(0, -1);
            Block b = new Block(-1, -1);
            Block c = new Block(-1, 0);

            blocks.Add(a);
            blocks.Add(b);
            blocks.Add(c);

            state = new State("Normal",1, blocks);
            List<State> states = new List<State>();
            states.Add(state);

            Piece piece = new Piece("Square","Square", states);

            return piece;
        }
        public Piece InitiateZRight()
        {
            
            List<Block> blocks = new List<Block>();
            List<State> states = new List<State>();

            Block a = new Block(0,-1);
            Block b = new Block(-1,0);
            Block c = new Block(-1,1);

            blocks.Add(a);
            blocks.Add(b);
            blocks.Add(c);

            State state = new State("Normal", 1, blocks);
            
            states.Add(state);

            blocks.Clear();

            Block a1 = new Block(1,0);
            Block b1 = new Block(0,-1);
            Block c1 = new Block(-1,-1);

            blocks.Add(a1);
            blocks.Add(b1);
            blocks.Add(c1);

            State state1 = new State("Upright", 2, blocks);

            states.Add(state1);

            Piece piece = new Piece("Zright", "Zright", states);

            return piece;
        }

        public Piece InitiateZLeft()
        {

            List<Block> blocks = new List<Block>();
            List<State> states = new List<State>();

            Block a = new Block(0,1);
            Block b = new Block(-1,-1);
            Block c = new Block(-1,0);

            blocks.Add(a);
            blocks.Add(b);
            blocks.Add(c);

            State state = new State("Normal", 1, blocks);

            states.Add(state);

            blocks.Clear();

            Block a1 = new Block(1,0);
            Block b1 = new Block(0,1);
            Block c1 = new Block(-1, 1);

            blocks.Add(a1);
            blocks.Add(b1);
            blocks.Add(c1);

            State state1 = new State("Upright", 2, blocks);

            states.Add(state1);

            Piece piece = new Piece("Zleft", "Zleft", states);

            return piece;
        }

        public Piece InitiateStraight()
        {

            List<Block> blocks = new List<Block>();
            List<State> states = new List<State>();

            Block a = new Block(1,0);
            Block b = new Block(-1,0);
            Block c = new Block(-2, 0);

            blocks.Add(a);
            blocks.Add(b);
            blocks.Add(c);

            State state = new State("Normal", 1, blocks);

            states.Add(state);

            blocks.Clear();

            Block a1 = new Block(0,-1);
            Block b1 = new Block(0, 1);
            Block c1 = new Block(0,2);

            blocks.Add(a1);
            blocks.Add(b1);
            blocks.Add(c1);

            State state1 = new State("Down", 2, blocks);

            states.Add(state1);

            Piece piece = new Piece("Straight", "Straight", states);

            return piece;
        }

        public Piece InitiateTriangle()
        {

            List<Block> blocks = new List<Block>();
            List<State> states = new List<State>();

            Block a = new Block(0,-1);
            Block b = new Block(1,0);
            Block c = new Block(0,1);

            blocks.Add(a);
            blocks.Add(b);
            blocks.Add(c);

            State state = new State("Normal", 1, blocks);

            states.Add(state);

            blocks.Clear();

            Block a1 = new Block(1,0);
            Block b1 = new Block(0,-1);
            Block c1 = new Block(-1,0);

            blocks.Add(a1);
            blocks.Add(b1);
            blocks.Add(c1);

            State state1 = new State("upleft", 2, blocks);

            states.Add(state1);

            blocks.Clear();

            Block a11 = new Block(1, 0);
            Block b11 = new Block(0, 1);
            Block c11 = new Block(-1, 0);

            blocks.Add(a11);
            blocks.Add(b11);
            blocks.Add(c11);

            State state11 = new State("down", 3, blocks);

            states.Add(state11);

            blocks.Clear();

            Block a2 = new Block(0,-1);
            Block b2 = new Block(0, 1);
            Block c2 = new Block(-1, 0);

            blocks.Add(a2);
            blocks.Add(b2);
            blocks.Add(c2);

            State state2 = new State("upright", 4, blocks);

            states.Add(state2);


            Piece piece = new Piece("Triangle", "Triangle", states);

            return piece;
        }

        public Piece InitiateLleft()
        {

            List<Block> blocks = new List<Block>();
            List<State> states = new List<State>();

            //Block a = new Block(2,0);
            Block b = new Block(1,0);
            Block c = new Block(0,-1);
            Block d = new Block(0, -2);
            //Block e = new Block(0, -3);

            //blocks.Add(a);
            blocks.Add(b);
            blocks.Add(c);
            blocks.Add(d);
            //blocks.Add(e);

            State state = new State("Normal", 1, blocks);

            states.Add(state);

            blocks.Clear();

           // Block a1 = new Block(0,-2);
            Block b1 = new Block(0,-1);
            Block c1 = new Block(-1, 0);
            Block d1 = new Block(-2,0);
            //Block e1 = new Block(-3, 0);

            //blocks.Add(a1);
            blocks.Add(b1);
            blocks.Add(c1);
            blocks.Add(d1);
            //blocks.Add(e1);

            State state1 = new State("upright", 2, blocks);

            states.Add(state1);

            blocks.Clear();

            //Block a11 = new Block(0,3);
            Block b11 = new Block(0,2);
            Block c11 = new Block(0,1);
            Block d11 = new Block(-1,0);
            //Block e11 = new Block(-2,0);

            //blocks.Add(a11);
            blocks.Add(b11);
            blocks.Add(c11);
            blocks.Add(d11);
            //blocks.Add(e11);

            State state11 = new State("right", 3, blocks);

            states.Add(state11);

            blocks.Clear();

            //Block a111 = new Block(3,0);
            Block b111 = new Block(2,0);
            Block c111 = new Block(1,0);
            Block d111 = new Block(0,1);
            //Block e111 = new Block(0,2);

            //blocks.Add(a111);
            blocks.Add(b111);
            blocks.Add(c111);
            blocks.Add(d111);
            //blocks.Add(e111);

            State state111 = new State("down", 4, blocks);

            states.Add(state111);


            Piece piece = new Piece("Lleft", "Lleft", states);

            return piece;
        }

        public Piece InitiateLright()
        {

            List<Block> blocks = new List<Block>();
            List<State> states = new List<State>();

            //Block a = new Block(2,0);
            Block b = new Block(1,0);
            Block c = new Block(0,1);
            Block d = new Block(0,2);
            //Block e = new Block(0, 3);

            //blocks.Add(a);
            blocks.Add(b);
            blocks.Add(c);
            blocks.Add(d);
            //blocks.Add(e);

            State state = new State("Normal", 1, blocks);

            states.Add(state);

            blocks.Clear();

            //Block a1 = new Block(3,0);
            Block b1 = new Block(2,0);
            Block c1 = new Block(1,0);
            Block d1 = new Block(0,-1);
           // Block e1 = new Block(0,-2);

           // blocks.Add(a1);
            blocks.Add(b1);
            blocks.Add(c1);
            blocks.Add(d1);
            //blocks.Add(e1);

            State state1 = new State("down", 2, blocks);

            states.Add(state1);

            blocks.Clear();

            //Block a11 = new Block(0,-3);
            Block b11 = new Block(0,-2);
            Block c11 = new Block(0,-1);
            Block d11 = new Block(-1,0);
            //Block e11 = new Block(-2,0);

            //blocks.Add(a11);
            blocks.Add(b11);
            blocks.Add(c11);
            blocks.Add(d11);
            //blocks.Add(e11);

            State state11 = new State("upright", 3, blocks);

            states.Add(state11);

            blocks.Clear();

            //Block a111 = new Block(0,2);
            Block b111 = new Block(0,1);
            Block c111 = new Block(-1, 0);
            Block d111 = new Block(-2,0);
            //Block e111 = new Block(-3,0);

            //blocks.Add(a111);
            blocks.Add(b111);
            blocks.Add(c111);
            blocks.Add(d111);
            //blocks.Add(e111);

            State state111 = new State("right", 4, blocks);

            states.Add(state111);


            Piece piece = new Piece("Lright", "Lright", states);

            return piece;
        }
    }
}
