and_(0,0,0).
and_(0,1,0).
and_(1,0,0).
and_(1,1,1).

or_(0,0,0).
or_(0,1,1).
or_(1,0,1).
or_(1,1,1).

not_(0,1).
not_(1,0).

xor_(X,Y,Z) :- not_(X,NX), not_(Y,NY), and_(X,NY,Z1),and_(NX,Y,Z2),or_(Z1,Z2,Z).
xnor_(X,Y,Z) :- not_(X,NX), not_(Y,NY), and_(X,Y,Z1),and_(NX,NY,Z2),or_(Z1,Z2,Z).



:- dynamic signal/2.
signal(a,0).
signal(b,1).
signal(c,1).
signal(d,1).
signal(e,1).

signal(X,Y) :-
	out(Gate,X),type(Gate,and),in(In1,1,Gate),in(In2,2,Gate),
	signal(In1,In1sig),signal(In2,In2sig),and_(In1sig,In2sig,Y).

signal(X,Y) :-
	out(Gate,X),type(Gate,or),in(In1,1,Gate),in(In2,2,Gate),
	signal(In1,In1sig),signal(In2,In2sig),or_(In1sig,In2sig,Y).

signal(X,Y) :-
	out(Gate,X),type(Gate,xor),in(In1,1,Gate),in(In2,2,Gate),
	signal(In1,In1sig),signal(In2,In2sig),xor_(In1sig,In2sig,Y).

signal(X,Y) :-
	out(Gate,X),type(Gate,xnor),in(In1,1,Gate),in(In2,2,Gate),
	signal(In1,In1sig),signal(In2,In2sig),xnor_(In1sig,In2sig,Y).

type(gateA,xnor).
type(gateB,xnor).
type(gateC,and).

in(a,1,gateA).
in(e,2,gateA).

in(b,1,gateB).
in(d,2,gateB).

in(p,1,gateC).
in(q,2,gateC).

out(gateB,q).
out(gateA,p).
out(gateC,s).















