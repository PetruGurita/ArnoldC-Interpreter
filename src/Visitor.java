

//visitor interface, with all the overloaded methods
public interface Visitor {
      public void visit(VariableNode varNode);
      public void visit(IfNode ifNode);
      public void visit(GreaterThanNode greaterNode);
      public void visit(EqualToNode equalNode);
      public void visit(IfBodyNode ifbodyNode);
      public void visit(ElseBodyNode elseBodyNode);
      public void visit(LvalNode lvalNode);
      public void visit(MainNode mainNode);
      public void visit(DifferenceNode minusNode);
      public void visit(DivisionOperatorNode divisionNode);
      public void visit(ModuloOperatorNode moduloNode);
      public void visit(MultiplicationOperatorNode multiplicationNode);
      public void visit(OrNode orNode);
      public void visit(SumOperatorNode plusNode);
      public void visit(PrintNode printNode);
      public void visit(RvalNode rvalNode);
      public void visit(StringNode stringNode);
      public void visit(DeclareNode declareNode);
      public void visit(ConstantNode constantNode);
      public void visit(ConditionNode conditionNode);
      public void visit(AssignmentNode assignNode);
      public void visit(AndNode andNode);    
      public void visit(TreeNode tree);
      public void visit(WhileNode whileNode);
      public void visit(BodyNode bodyNode);
}
