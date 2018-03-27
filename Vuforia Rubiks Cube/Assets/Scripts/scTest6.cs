using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;

public class scTest6 : MonoBehaviour, IVirtualButtonEventHandler {

	public GameObject vbBtnTest;
	//public Animator cubeAnim;
	public GameObject empty;

	public void Start () {
		//Debug.Log(this.transform.GetChild(0).name);
		
		vbBtnTest.GetComponent<VirtualButtonBehaviour>().RegisterEventHandler(this);
	}

	public void OnButtonPressed(VirtualButtonAbstractBehaviour vb)
	{
		//Debug.Log("pressed");
		// foreach(Transform gobj in this.transform)
		// {
		// 	if(gobj.tag=="test")
		// 	{
		// 		Debug.Log(gobj.name);
		// 	}
		// }
		List<GameObject> objs=new List<GameObject>();
		float x=.12f,z=-.12f,y=.12f;
		for(int i=1;i<4;i++)
		{
			for(int j=-1;j<2;j++)
			{
				if(x!=0 || y!=.24f)
				{
					GameObject obj=FindAt(new Vector3((float)(x*j),(float)(y*i),(float)(z)));
					//Debug.Log(obj.name);
					obj.transform.parent = empty.transform; 
					objs.Add(obj);
				}
			}
		}
		//cubeAnim.Play("Empty 020 Clockwise");
		empty.transform.Rotate(0,0,90);
		foreach(GameObject o in objs)
		{
			o.transform.parent=empty.transform.parent;
			//Debug.Log(o.transform.parent.name);
		}
	}
	
	public void OnButtonReleased(VirtualButtonAbstractBehaviour vb)
	{
		//Debug.Log("released");
		//cubeAnim.Play("none");
	}

	public GameObject FindAt(Vector3 pos)
	{
   		// get all colliders that intersect pos:
   		Collider[] cols = Physics.OverlapSphere(pos, (float)0.01);
   		// find the nearest one:
   		float dist = float.MaxValue;
   		GameObject nearest=null;
   		foreach (Collider col in cols)
		{
     		// find the distance to pos:
     		var d = Vector3.Distance(pos, col.transform.position);
			if (d < dist)
			{
				 // if closer...
       			dist = d; // save its distance... 
       			nearest = col.gameObject; // and its gameObject
     		}
   		}
   		return nearest;
 	}
}
