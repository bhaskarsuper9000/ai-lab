and_(0,0,0).
and_(0,1,0).
and_(1,0,0).
and_(1,1,1).

or_(0,0,0).
or_(0,1,1).
or_(1,0,1).
or_(1,1,1).

and3_(0,0,0,0).
and3_(0,0,1,0).
and3_(0,1,0,0).
and3_(0,1,1,0).
and3_(1,0,0,0).
and3_(1,0,1,0).
and3_(1,1,0,0).
and3_(1,1,1,1).

or3_(0,0,0,0).
or3_(0,0,1,1).
or3_(0,1,0,1).
or3_(0,1,1,1).
or3_(1,0,0,0).
or3_(1,0,1,1).
or3_(1,1,0,1).
or3_(1,1,1,1).

:- dynamic signal/2.
signal(a,0).
signal(b,0).
signal(c,0).
signal(d,0).
signal(e,0).

signal(X,Y) :-
	out(Gate,X),type(Gate,and),in(In1,1,Gate),in(In2,2,Gate),
	signal(In1,In1sig),signal(In2,In2sig),and_(In1sig,In2sig,Y).

signal(X,Y) :-
	out(Gate,X),type(Gate,or),in(In1,1,Gate),in(In2,2,Gate),
	signal(In1,In1sig),signal(In2,In2sig),or_(In1sig,In2sig,Y).

signal(X,Y) :-
	out(Gate,X),type(Gate,and3), in(In1,1,Gate), in(In2,2,Gate),in(In3,3,Gate),signal(In1,In1sig),signal(In2,In2sig),signal(In3,In3sig),and3_(In1sig,In2sig, In3sig,Y).

signal(X,Y) :-
	out(Gate,X),type(Gate,or3), in(In1,1,Gate), in(In2,2,Gate),in(In3,3,Gate),signal(In1,In1sig),signal(In2,In2sig),signal(In3,In3sig),or3_(In1sig,In2sig, In3sig,Y).

%circuit specific info
type(gate1,and).
type(gate2,and).
type(gate3,and).
type(gate4,and).
type(gate5,or).
type(gate6,or).
type(gate7,or3).
type(gate8,or3).
type(gate9,or3).
type(gate10,and3).

in(a,1,gate1).
in(b,2,gate1).
out(gate1,p).

in(d,1,gate2).
in(e,2,gate2).
out(gate2,t).

in(p,1,gate3).
in(q,2,gate3).
out(gate3,v).

in(t,1,gate4).
in(u,2,gate4).
out(gate4,x).

in(a,1,gate5).
in(b,2,gate5).
out(gate5,r).

in(e,1,gate6).
in(d,2,gate6).
out(gate6,s).

in(c,1,gate7).
in(d,2,gate7).
in(e,3,gate7).
out(gate7,q).

in(a,1,gate8).
in(b,2,gate8).
in(c,3,gate8).
out(gate8,u).

in(v,1,gate9).
in(w,2,gate9).
in(x,3,gate9).
out(gate9,z).

in(r,1,gate10).
in(s,2,gate10).
in(c,3,gate10).
out(gate10,w).











