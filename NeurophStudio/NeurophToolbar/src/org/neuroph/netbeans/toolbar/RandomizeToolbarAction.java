package org.neuroph.netbeans.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.netbeans.main.easyneurons.NeuralNetworkTraining;
import org.neuroph.netbeans.visual.GraphViewTopComponent;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

@ActionID(
        category = "Neuroph",
        id = "org.neuroph.netbeans.toolbar.RandomizeToolbarAction")
@ActionRegistration(
        iconBase = "org/neuroph/netbeans/toolbar/icons/randomize.png",
        displayName = "#CTL_RandomizeToolbarAction")
@ActionReference(path = "Toolbars/Neuroph", position = -500)
@Messages("CTL_RandomizeToolbarAction=Randomize")
public final class RandomizeToolbarAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Lookup global = Utilities.actionsGlobalContext();
        NeuralNetwork nnet = global.lookup(NeuralNetwork.class);
        if (nnet == null) {
            JOptionPane.showMessageDialog(null, "Neural network is not selected!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        NeuralNetworkTraining training = new NeuralNetworkTraining(nnet);
        training.randomize();
        
        TopComponent graph = TopComponent.getRegistry().getActivated();
       if (graph instanceof GraphViewTopComponent) {
            ((GraphViewTopComponent) graph).refresh();
        }
    }
}