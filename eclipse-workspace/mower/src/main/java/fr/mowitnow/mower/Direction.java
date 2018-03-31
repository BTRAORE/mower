/**
 * 
 */
package fr.mowitnow.mower;

/**
 * @author Brehima
 *Represente les orientations (Nord(N), Sud(S), Est(E), Ouest(O))
 */
public enum Direction implements DirectionChanger {
	
	N{
		@Override
		public Direction left() {
			return W;
		}

		@Override
		public Direction right() {
			return E;
		}
	},
	S{
		@Override
		public Direction left() {
			return E;
		}

		@Override
		public Direction right() {
			return W;
		}
	},
	E{
		@Override
		public Direction left() {
			return N;
		}

		@Override
		public Direction right() {
			return S;
		}
	},
	W{
		@Override
		public Direction left() {
			return S;
		}

		@Override
		public Direction right() {
			return N;
		}
	};

}
