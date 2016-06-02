using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace Leetris
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        Tool tool = new Tool();
        public MainWindow()
        {

            InitializeComponent();
            ClearGrid();
        }
        int FlashHeight;


        //starts or ends a game
        public void Starter()
        {
            if (!tool.Started)
            {
                //button1.Content = "Stop";
                tool.Started = true;

                ClearGrid();
                LoadShape();
                tool.Countdown.Start();
                tool.FlashPlayer.Start();
                //FlashContainer.Visibility = System.Windows.Visibility.Hidden;
                GridContainer.Visibility = System.Windows.Visibility.Visible;
                tool.ElapsedSeconds = 0;
                tool.TimeKeeper.Start();
                tool.Points = 0;
            }
            else
            {
                tool.Started = false;
                tool.Countdown.Stop();
                ClearGrid();
                //button1.Content = "Start";
                tool.FlashPlayer.Stop();
                FlashContainer.Height = FlashHeight;
                FlashContainer.Visibility = System.Windows.Visibility.Visible;
                GridContainer.Visibility = System.Windows.Visibility.Hidden;
            }
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            Starter();
            
        }

        //sets the free property of all the blocks in the grid to true
        public void ClearGrid()
        {
            foreach (GridBlock gb in GridContainer.Children)
            {
                gb.Background = getMyImage("Empty");
                gb.Content = "";// gb.Y.ToString() + gb.X.ToString();
                gb.IsFree = true;
            }
        }

        //returns an image brush with the name passed from the image content folder
        //the image's extension is png

        public ImageBrush getMyImage(string choice)
        {
            ImageBrush bi = new ImageBrush();
            bi.ImageSource = new BitmapImage(new Uri(@"pack://application:,,/Leetris;component/Images/" + choice + ".png"));
            //bi.Stretch = Stretch.Fill;
            //bi.Stretch = Stretch.Uniform;

            //return new Uri((@"pack://application:,,/Brian's%20Bubbles;component/Images/" + choice + ".png"));
            return bi;
        }

        //Returns a gridblock with the same coordinates

        public GridBlock GetABlock(int y, int x)
        {
            GridBlock gb = new GridBlock();

            foreach (GridBlock current in GridContainer.Children)
            {
                if (x == current.X && y == current.Y)
                {
                    gb = current;
                    break;
                }
            }
            return gb;
        }

        //uses the gridblock with passed x and y
        //sets its free property to false
        //sets its background to the imagebrush passed
        public void UseGridBlock(int y, int x, ImageBrush image)
        {
            

            foreach (GridBlock current in GridContainer.Children)
            {
                if (x == current.X && y == current.Y)
                {
                    current.IsFree = false;
                    current.Background = image;
                    //current.Content = "";
                    break;
                }
            }
            
        }

        //sets the free property of the grind with x and y to true
        public void FreeGridBlock(int y, int x, ImageBrush image)
        {


            foreach (GridBlock current in GridContainer.Children)
            {
                if (x == current.X && y == current.Y)
                {
                    current.IsFree = true;
                    //current.Content = "Free";
                    current.Background = image;
                    break;
                }
            }

        }

        //Gets a new Tetris piece and displays it on the screen
        public void LoadShape()
        {
            firstMove = true;
            reachedBottom = true;
            //tool.InitiatePieces();
            tool.Down = 0;
            tool.Left = 0;

            freeCurrent();
            int number = tool.Pieces.Count;

            Piece piece = tool.Pieces[RandNum(number)];
            tool.CurrentPiece = tool.GetPiece(piece.Name);

            tool.CurrentState = piece.GetNormalState();
            tool.CurrentStateNumber = tool.CurrentState.Number;
            
            
            if (!gridIsFull())
            {
                if (!FalseLaunch() && tool.CurrentState.Blocks.Count > 0)
                {
                    MoveUp();
                    foreach(Block bb in tool.CurrentState.Blocks)
                    {
                        UseGridBlock(bb.Y, bb.X, getMyImage(piece.Image));
                        tool.CurrentBlocks.Add(bb);
                    }
                    
       

                }
                else
                {
                    //subtrasct from y and x
                    Piece piece1 = tool.Pieces[RandNum(number)];
                    tool.CurrentPiece = tool.GetPiece(piece1.Name);

                    tool.CurrentState = piece.GetNormalState();
                    tool.CurrentStateNumber = tool.CurrentState.Number;
                    //MessageBox.Show("number of blocks " +tool.CurrentState.Blocks.Count.ToString());

                    if (tool.CurrentState.Blocks.Count > 0)
                    {
                        MoveToStartingPosition();

                        MoveUp();
                        foreach (Block bb in tool.CurrentState.Blocks)
                        {
                            UseGridBlock(bb.Y, bb.X, getMyImage(piece.Image));
                            tool.CurrentPiece.Image = piece.Image;
                            tool.CurrentBlocks.Add(bb);
                        }
                    }
                    else
                    {
                        LoadShape();
                    }

                }//
                    //s.Add(new Block(0, 0));
            }
            else
            {
                
                //button1.Content = "Start";

                EndGame();
            }
                    //
        }

        //returns an integer value that needs to be added to y
        //for the piece to be in the right starting position reference to y
        public int getValueToAddToY()
        {
            int m = 0;

            foreach (Block bb in tool.CurrentState.Blocks)
            {
                if (bb.Center)
                {
                    m = bb.Y;
                    break;
                }
            }

            if (m < 0)
            {
                m = m * -1;
            }

            return m;
        }


        //returns an integer value that nees to be added to x
        //for the piece to be in the right starting position reference to x
        public int getValueToAddToX()
        {
            int m = 0;

            foreach (Block bb in tool.CurrentState.Blocks)
            {
                if (bb.Center)
                {
                    m = bb.X;
                    break;
                }
            }

            if (m > 0)
            {
                m = m * -1;
            }
            else
            {
                m += 4;
            }

            return m;
        }

        //adds to x and y of the current state's blocks so the are all in
        //the right starting position reference to both x and y
        public void MoveToStartingPosition()
        {
            int y = getValueToAddToY();
            int x = getValueToAddToX();

            foreach (Block bb in tool.CurrentState.Blocks)
            {
                bb.Y = bb.Y + y;
                bb.X = bb.X + x;

                
            }
        }

        //returns true if the new piece's values are not in the
        //starting position reference to y
        public bool FalseLaunch()
        {
            bool yes = false;

            foreach (Block bb in tool.CurrentState.Blocks)
            {
                if (bb.Y < -4)
                {
                    yes = true;
                    break;
                }
            
            }
            return yes;
        }

        //frees the current blocks 
        public void freeCurrent()
        {
            //foreach (Block bb in tool.CurrentBlocks)
            //{
            //    FreeGridtrue;
                    //break;ty"));
            //}
         
            //return yes;w State();
            tool.CurrentBlocks.Clear();
            tool.CurrentState = new State();
        }


        //Moves the current piece to the right
        public void MoveLeft()//moves right
        {
            bool canMove = CanMoveLeft();
            //List<Block> currentBlocks = tool.CurrentBlocks;
            tool.Left++;
            if (canMove)
            {

                foreach (Block bb in tool.CurrentState.Blocks)
                {
                    //Block bb = tool.CurrentBlocks[m];
                    int x = bb.X;
                    int y = bb.Y;


                    FreeGridBlock(bb.Y, bb.X, getMyImage("Empty"));
                    //bb = new Block(y, x + 1);
                    //UseGridBlock(bb.Y, bb.X + tool.Left, getMyImage(tool.CurrentPiece.Image));

                }
                foreach (Block bb in tool.CurrentState.Blocks)
                {
                    //Block bb = tool.CurrentBlocks[m];
                    int x = bb.X;
                    int y = bb.Y;


                    bb.X = bb.X + 1;

                    //bb = new Block(y, x + 1);
                    UseGridBlock(bb.Y, bb.X, getMyImage(tool.CurrentPiece.Image));


                }
            }
            else
            {
                //if (!CanMoveDown())
                //{
                //    LoadShape();
                //}
            }

            
        }


        //moves the current piece to the left
        public void MoveRight()//moves left
        {
            bool canMove = CanMoveright();
            //List<Block> currentBlocks = tool.CurrentBlocks;
            tool.Left--;
            if (canMove)
            {

                foreach (Block bb in tool.CurrentState.Blocks)
                {
                    //Block bb = tool.CurrentBlocks[m];
                    int x = bb.X;
                    int y = bb.Y;


                    FreeGridBlock(bb.Y, bb.X, getMyImage("Empty"));
                    //bb = new Block(y, x + 1);
                    //UseGridBlock(bb.Y, bb.X + tool.Left, getMyImage(tool.CurrentPiece.Image));
                }


                foreach (Block bb in tool.CurrentState.Blocks)
                {
                    //Block bb = tool.CurrentBlocks[m];
                    int x = bb.X;
                    int y = bb.Y;


                    bb.X = bb.X - 1;

                    //bb = new Block(y, x + 1);
                    UseGridBlock(bb.Y, bb.X, getMyImage(tool.CurrentPiece.Image));
                }

            }
            else
            {
                //if (!CanMoveDown())
                //{
                //    LoadShape();
                //}
            }


        }
        bool firstMove = true;

        //moves the current piece down
        public void Movedown()
        {
            bool canMove = CanMoveDown();
            //List<Block> currentBlocks = tool.CurrentBlocks;
            tool.Down--;
            if (canMove)
            {
                firstMove = false;
                reachedBottom = false;
                foreach (Block bb in tool.CurrentState.Blocks)
                {
                    //Block bb = tool.CurrentBlocks[m];
                    int x = bb.X;
                    int y = bb.Y;


                    FreeGridBlock(bb.Y, bb.X, getMyImage("Empty"));
                    //bb = new Block(y, x + 1);
                    //UseGridBlock(bb.Y, bb.X + tool.Left, getMyImage(tool.CurrentPiece.Image));
                }
                //else
                //{
                //    break;
                //}


                foreach (Block bb in tool.CurrentState.Blocks)
                {
                    //Block bb = tool.CurrentBlocks[m];
                    int x = bb.X;
                    int y = bb.Y;


                    bb.Y = bb.Y - 1;

                    //bb = new Block(y, x + 1);
                    UseGridBlock(bb.Y, bb.X, getMyImage(tool.CurrentPiece.Image));
                }
                if (!CanMoveDown())
                {
                    EatFullRows();
                }

                //else
                //{
                //    break;
                //}


            }
            else
            {
                //reachedBottom = true;
                if (!firstMove)
                {
                    EatFullRows();

                    LoadShape();
                }
                else
                {
                    //game over
                    EndGame();
                }
            }


        }

        public void EndGame()
        {
            FlashContainer.Height = FlashHeight;
            FlashContainer.Visibility = System.Windows.Visibility.Visible;
            GridContainer.Visibility = System.Windows.Visibility.Hidden;
            ClearGrid();
            tool.FlashPlayer.Stop();
            MessageBox.Show("You Scored: " + tool.Points.ToString() + " Points", "Game Over");
            tool.Countdown.Stop();
            tool.Started = false;
        }

        public void MoveUp()
        {
            //foreach (Block bb in tool.CurrentState.Blocks)
            //{
            //    //Block bb = tool.CurrentBlocks[m];
            //    int x = bb.X;
            //    int y = bb.Y;


            //    FreeGridBlock(bb.Y, bb.X, getMyImage("Empty"));
            //    //bb = new Block(y, x + 1);
            //    //UseGridBlock(bb.Y, bb.X + tool.Left, getMyImage(tool.CurrentPiece.Image));
            //}
            //else
            //{
            //    break;
            //}


            foreach (Block bb in tool.CurrentState.Blocks)
            {
                //Block bb = tool.CurrentBlocks[m];
                int x = bb.X;
                int y = bb.Y;


                bb.Y = bb.Y + 2;

                //bb = new Block(y, x + 1);
                //UseGridBlock(bb.Y, bb.X, getMyImage(tool.CurrentPiece.Image));
            }
        }

        //checks if the piece can be moved
        public bool CanMoveLeft()
        {
            foreach (Block bb in tool.CurrentState.Blocks)
            {
                //Block bb = tool.CurrentBlocks[m];


                if (bb.X + 1 >= 6)
                {
                    return false;
                }
                GridBlock bb1 = getGridBlock(bb.Y, bb.X + 1);

                if (!bb1.IsFree && bb1 != null && notInThese(bb.Y, bb.X + 1))
                {
                    return false;
                }
            }
            return true;
        }

        public bool CanMoveright()
        {
            foreach (Block bb in tool.CurrentState.Blocks)
            {
                //Block bb = tool.CurrentBlocks[m];


                if (bb.X - 1 <=-5)
                {
                    return false;
                }
                GridBlock bb1 = getGridBlock(bb.Y, bb.X - 1);

                if (!bb1.IsFree && bb1 != null && notInThese(bb.Y, bb.X - 1))
                {
                    return false;
                }
            }
            return true;
        }

        //checks if all the blocks points are within the grid
        public bool isWithinRange()
        {
            bool yes = true;
            foreach (Block bb in tool.CurrentState.Blocks)
            {
                if (bb.Y - 1 <= -18)
                {
                    yes = false;
                    break;
                }
            }
            return yes;
        }

        public bool CanMoveDown()
        {
            bool isfree = GridBlockisFree();
            
                //Block bb = tool.CurrentBlocks[m];


            if (!isWithinRange())
            {

                tool.CurrentBlocks.Clear();
                //EatFullRows();

                return false;
                

            }

                if (isfree)
                {
                    tool.CurrentBlocks.Clear();

                    return true;
                }
                else
                {
                    //EatFullRows();
                    return false;
                }
                
                    
            
            
        }

        //checks if the grid is full
        public bool gridIsFull()
        {
            bool yes = false;
            
            

            //GridBlock bb = getGridBlock(2, 0);
            //GridBlock bb1 = getGridBlock(1, 0);

            if (LoadingZoneFree())
            {
                yes = false;
            }
            else if(!CanMoveDown())
            {
                yes = true;
            }
            
            
            //{
            //    yes = true;
            //}

            return yes;
        }

        public bool LoadingZoneFree()
        {
            GridBlock bb = getGridBlock(2, 0);
            GridBlock bb1 = getGridBlock(1, 0);
            GridBlock bb2 = getGridBlock(0, 0);
            GridBlock bb3 = getGridBlock(-1, 0);
            GridBlock bb4 = getGridBlock(-2, 0);
            GridBlock bb5 = getGridBlock(-3, 0);

            List<GridBlock> blocks = new List<GridBlock>();

            blocks.Add(bb2);
            blocks.Add(bb3);
            blocks.Add(bb4);
            blocks.Add(bb5);

            int count = 0;

            foreach (GridBlock bbb in blocks)
            {
                if (!bbb.IsFree)
                    count++;
            }

            if (count >= 2 && !bb.IsFree && !bb1.IsFree)
            {
                return false;
            }
            return true;

        }

        public bool FreeToStart()
        {
            bool allfree = true;
            //int yvalue = getSmallestYValue();

            foreach (Block b in tool.CurrentState.Blocks)
            {
                GridBlock gb = getGridBlock(b.Y, b.X);

                if (!gb.IsFree)
                {
                    allfree = false;
                    break;
                }
            }
            return allfree;
        }

        public bool GridBlockisFree()
        {
            bool allfree = true;
            //int yvalue = getSmallestYValue();

            foreach(Block b in tool.CurrentState.Blocks)
            {
                GridBlock gb = getGridBlock(b.Y - 1, b.X);

                if (!gb.IsFree && notInThese(gb.Y, gb.X))
                {
                    allfree = false;
                    break;
                }
            }
            return allfree;
        }

        //checks if the passed block coordinates are not in the current state
        public bool notInThese(int y, int x)
        {
            foreach (Block bb in tool.CurrentState.Blocks)
            {
                if (bb.Y == y && bb.X == x)
                {
                    return false;
                }
            }
            return true;
        }


        public int getSmallestYValue()
        {
            int temp = 0;

            foreach(Block bb  in tool.CurrentState.Blocks)
            {
                if (bb.Y - 1 < temp)
                {
                    temp = bb.Y ;
                }
            }
            return temp;
        }

        //returns a gridblock that has the same coordinates as the ones passed
        public GridBlock getGridBlock(int y, int x)
        {
            GridBlock gb = new GridBlock();

            foreach (GridBlock g in GridContainer.Children)
            {
                if (g.X == x && g.Y == y)
                {
                    gb = g;
                    break;
                }
            }
            return gb;
        }

        public int RandNum(int limit)
        {
            Random rd = new Random(DateTime.Now.Millisecond);

            return  rd.Next(0, limit);
        }

        private void button2_Click(object sender, RoutedEventArgs e)
        {
            if (tool.Started)
            MoveLeft();
        }

        private void button3_Click(object sender, RoutedEventArgs e)
        {
            if (tool.Started)
                //Movedown();
                DropPiece();
        }

        private void button4_Click(object sender, RoutedEventArgs e)
        {
            if(tool.Started)
            MoveRight();
        }

        #region Flip Piece

        public Piece GetSamePiece(string name)
        {
            Piece piece = new Piece();

            foreach (Piece pc in tool.Pieces)
            {
                if (pc.Name.ToLower() == name.ToLower())
                {
                    piece = pc;
                    break;
                }
            }
            return piece;
        }
        //Rotates the current piece
        public void flipPiece()
        {
            tool.InitiatePieces();
            
            //int number = tool.Pieces.Count;
            tool.CurrentPiece = tool.GetPiece(tool.CurrentPiece.Name);

            Piece piece = tool.CurrentPiece;
            //tool.CurrentPiece = piece;

            State state = piece.GetNextState(tool.CurrentStateNumber);
            //tool.CurrentState = piece.GetNextState(tool.CurrentStateNumber);
            bool yesCanFlip = CanFlip(state);

            if (yesCanFlip && (piece.States.Count > 1))
            {
                //state.Blocks.Add(new Block(0, 0));
                freeCurrent1();

                tool.CurrentState = state;
                //return state to original, sure will flip
                
                tool.CurrentStateNumber = tool.CurrentState.Number;
                


                PrepareState();

                foreach (Block bb in tool.CurrentState.Blocks)
                {
                    UseGridBlock(bb.Y, bb.X, getMyImage(piece.Image));
                    tool.CurrentBlocks.Add(bb);
                }
            }

            //UseGridBlock(0, 0, getMyImage(piece.Image));
            //tool.CurrentBlocks.Add(new Block(0, 0));

        }

        //adds down and side to the coordinates of the current state
        public void PrepareState()
        {
            foreach (Block bb in tool.CurrentState.Blocks)
            {

                bb.X += tool.Left;
                bb.Y += tool.Down;
            }
        }


        //flipPiece2 doesnt work
        public void flipPiece2()
        {
            //tool.InitiatePieces();

            ////int number = tool.Pieces.Count;
            //tool.CurrentPiece = GetSamePiece(tool.CurrentPiece.Name);

            Piece piece = tool.CurrentPiece;
            ///tool.CurrentPiece = piece;

            //State state = piece.GetNormalState(); //tool.CurrentPiece.GetNormalState(); //tool.CurrentState; //piece.GetNextState(tool.CurrentStateNumber);
            //tool.CurrentState = piece.GetNextState(tool.CurrentStateNumber);

            //if (CanFlip(state)  /*&&(piece.States.Count > 1)*/)
            //{
                //state.Blocks.Add(new Block(0, 0));
                freeCurrent1();

                //tool.CurrentState = state;
                //return state to original, sure will flip
                
                //tool.CurrentStateNumber = tool.CurrentState.Number;



                //PrepareState();

                foreach (Block bb in tool.CurrentState.Blocks)
                {
                    if (!(/*!bb.Center*/ bb.X == 0 && bb.Y == 0))
                    {
                        int x = bb.X;
                        int y = bb.Y;

                        int temp = x;
                        x = y * -1;
                        y = temp;

                        //newPoints(ref x, ref y);

                        bb.X = x;
                        bb.Y = y;
                    }


                    UseGridBlock(bb.Y, bb.X, getMyImage(piece.Image));
                    tool.CurrentBlocks.Add(bb);
                }
            //}

            //UseGridBlock(0, 0, getMyImage(piece.Image));
            //tool.CurrentBlocks.Add(new Block(0, 0));

        }

        public void newPoints(ref int x, ref int y)
        {
            //(y,x) (-x, y)
            int temp = x ;
            int temp2 = y * -1;

            y = temp ;
            x = temp2 ;


        }

        //checks if all the coordinates will be within the grid
        //after the piece has be fliped
       public bool CanFlip(State state)
        {
            bool canFlip = true;

            foreach (Block bb in state.Blocks)
            {

                if ((bb.X + tool.Left >= 6) || (bb.X + tool.Left <= -5) ||
                (bb.Y + tool.Down <= -18))
                {
                    return false;
                }
                GridBlock gb = getGridBlock(bb.Y + tool.Down, bb.X + tool.Left);
                if (!gb.IsFree && gb != null && notInThese(gb.Y, gb.X))
                {
                    return false;
                }
            }

            return canFlip;
        }
        public void freeCurrent1()
        {
            foreach (Block bb in tool.CurrentState.Blocks)
            {
                FreeGridBlock(bb.Y, bb.X, getMyImage("Empty"));
            }
            tool.CurrentBlocks.Clear();
            //tool.CurrentState.Blocks.Clear();
            //tool.CurrentState = new State();
        }

        private void button5_Click(object sender, RoutedEventArgs e)
        {
            if (tool.Started)
            flipPiece();
        }

        #endregion Flip Piece

        #region Piece Control

        private void Window_PreviewKeyDown(object sender, KeyEventArgs e)
        {
            if (tool.Started)
            {
                if (e.Key == Key.Left)
                {
                    if (tool.Started)
                        e.Handled = true;
                    MoveRight();
                }
                else if (e.Key == Key.Right)
                {
                    if (tool.Started)
                        e.Handled = true;
                    MoveLeft();
                }
                else if (e.Key == Key.Down)
                {
                    if (tool.Started)
                        e.Handled = true;
                    //Movedown();
                    DropPiece();
                }
                else if (e.Key == Key.Up)
                {
                    if (tool.Started)
                        e.Handled = true;
                    flipPiece();
                }
            }
            if (e.Key == Key.Space || e.Key == Key.Return)
            {
                Starter();
            }

        }
        #endregion Piece Control

        public void AddToPoints(int numOfRows)
        {
            int tempValue = 0;

            if (numOfRows == 1)
            {
                tempValue = 5;
            }
            else if (numOfRows == 2)
            {
                tempValue = 10;
            }
            else if (numOfRows == 3)
            {
                tempValue = 20;
            }
            else if (numOfRows == 4)
            
            {
                Smile.Visibility = System.Windows.Visibility.Visible;
                tempValue = 50;
            }
            else if (numOfRows == 0)
            {
                tempValue = 0;
            }
            else
            {
                tempValue = 100;
                Smile.Visibility = System.Windows.Visibility.Visible;
            }
            tool.Points += tempValue;
        }

        //removes all the full rows, and adds to the score card
        public void EatFullRows()
        {
            //int count = 14;
            List<int> RowsToClear = new List<int>();
            //while (count > 1)
            //{

            for (int m = -17; m < 2; m++)//y
            {
                if (rowIsFull(m))
                {
                    RowsToClear.Add(m);
                    //clear the row First

                }
            }
            if (RowsToClear.Count > 0)
            {
                AddToPoints(RowsToClear.Count);
                foreach (int m in RowsToClear)
                {
                    ClearRow(m);
                    //MoveRowsAboveDown(m);

                }
            }
            //foreach (int m in RowsToClear)
            //{
            //    //ClearRow(m);
            //    MoveRowsAboveDown(m);

            //}

            int count = 3;// 5;

            while (count > 0)
            {
                for (int m = -17; m < 2; m++)//y
                {
                    if (rowIsEmpty(m))
                    {
                        MoveRowsAboveDown(m);
                        //RowsToClear.Add(m);
                        //clear the row First

                    }
                }
                count--;
            }

            // }
            LoadShape();
        }

        //checks if a row is full
        public bool rowIsFull(int rowNum)
        {
            bool isFull = true;

            for (int n = -4; n < 6; n++)//x
            {
                GridBlock bb = getGridBlock(rowNum, n);

                if (bb.IsFree)
                {
                    isFull = false;
                    break;
                }
            }
            return isFull;
        }


        //checks if a ro is empty
        public bool rowIsEmpty(int rowNum)
        {
            bool isEmpty = true;

            for (int n = -4; n < 6; n++)//x
            {
                GridBlock bb = getGridBlock(rowNum, n);

                if (!bb.IsFree)
                {
                    isEmpty = false;
                    break;
                }
            }
            return isEmpty;
        }


        //moves down all the rows that are above the passed row
        public void MoveRowsAboveDown(int rowNum)
        {
            for (int m = rowNum; m < 2; m++)
            {
                for (int n = -4; n < 6; n++)
                {
                    GridBlock ToMoveTo = getGridBlock(m, n);
                    GridBlock ToMoveFrom = getGridBlock(m + 1, n);

                    //current.IsFree = false;
                    //current.Background = image;

                    ToMoveTo.Background = ToMoveFrom.Background;
                    ToMoveTo.IsFree = ToMoveFrom.IsFree;

                    ToMoveFrom.IsFree = true;
                    ToMoveFrom.Background = getMyImage("Empty");
                }
            }
        }
        public void ClearRow(int RowNum)
        {
            tool.CurrentState.Blocks.Clear();
            for (int n = -4; n < 6; n++)
            {
                GridBlock GridToClear = getGridBlock(RowNum, n);
                GridToClear.IsFree = true;
                GridToClear.Background = getMyImage("Empty");
            }
        }


        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            tool.Countdown = new DispatcherTimer();
            tool.Countdown.Interval = new TimeSpan(0, 0, 0, 0,1000);
            tool.Countdown.Tick += new EventHandler(CountdownTimerStep);

            tool.FlashPlayer = new DispatcherTimer();
            tool.FlashPlayer.Interval = new TimeSpan(0, 0, 0, 0, 1);
            tool.FlashPlayer.Tick += new EventHandler(PlayFlash);

            tool.TimeKeeper = new DispatcherTimer();
            tool.TimeKeeper.Interval = new TimeSpan(0, 0, 1);
            tool.TimeKeeper.Tick += new EventHandler(EverySecond);



            FlashContainer.Visibility = System.Windows.Visibility.Visible;
            FlashHeight = int.Parse(FlashContainer.Height.ToString());
            GridContainer.Visibility = System.Windows.Visibility.Hidden;
            Smile.Visibility = System.Windows.Visibility.Hidden;
        }
        int countdown = 1000;
        int smilecount = 2;

        private void EverySecond(object sender, EventArgs e)
        {
            if (tool.Started)
            {
                tool.ElapsedSeconds++;
                //Smile.Visibility = System.Windows.Visibility.Hidden;

                if (tool.ElapsedSeconds >= tool.TimeMilestone)
                {
                    countdown -= 100;

                    tool.Countdown.Interval = new TimeSpan(0, 0, 0, 0, countdown);
                    //tool.TimeMilestone = tool.TimeMilestone * 2;
                    tool.ElapsedSeconds = 0;
                }

                if (Smile.Visibility == System.Windows.Visibility.Visible )
                {
                    if (smilecount > 0)
                    {
                        smilecount--;
                    }
                    else
                    {
                        Smile.Visibility = System.Windows.Visibility.Hidden;
                        smilecount = 2;
                    }
                }
            }
            else
            {
                tool.ElapsedSeconds = 0;
            }
        }

        private void PlayFlash(object sender, EventArgs e)
        {
            if (tool.Started)
            {
                if (FlashContainer.Height - 2 >= 0)
                {
                    FlashContainer.Height -= 2;
                }
            }
            else
            {
                FlashContainer.Visibility = System.Windows.Visibility.Hidden;
                
                //tool.Countdown.Interval = new TimeSpan(0, 0, 0, 0, 700);
                tool.FlashPlayer.Stop();
            }
        }

        private void CountdownTimerStep(object sender, EventArgs e)
        {
            if (tool.Started)
            {
                Movedown();
            }
        }

        //moves the piece down until it can't be moved any further
        public void DropPiece()
        {
            for (int m = 0; m < 20; m++)
            {
                if (!reachedBottom)
                {
                    Movedown();
                }
                else
                {
                    break;
                }
            }
        }
        bool reachedBottom = false;
    }
}
